#!/bin/sh

notify() {
	# we don't really depend on the hotplug_e2_helper, but when it exists, call it
	if [ -x /usr/bin/hotplug_e2_helper ] ; then
		/usr/bin/hotplug_e2_helper $ACTION /block/$MDEV $PHYSDEVPATH
	fi
}

case "$ACTION" in
	add|"")
		ACTION="add"
		# check if already mounted
		if grep -q "^/dev/${MDEV} " /proc/mounts ; then
			# Already mounted
			exit 0
		fi
		if [ "${MDEV}" = "`readlink /dev/root`" ]; then
			# Special case
			exit 0
		fi
		DRIVER=`expr substr $MDEV 1 6`
		if [ "$DRIVER" = "mmcblk" ]; then
			DEVBASE=`expr substr $MDEV 1 7`
		else
			DEVBASE=`expr substr $MDEV 1 3`
		fi
		# check for "please don't mount it" file
		if [ -f "/dev/nomount.${DEVBASE}" ] ; then
			# blocked
			exit 0
		fi
		# Run the result of blkid as a shell command
		eval `blkid /dev/${MDEV} | grep ${MDEV} | cut -d ':' -f 2`
		if [ -z "$TYPE" ] ; then
			notify
			exit 0
		fi
		if [ $TYPE == swap ] ; then
			if ! grep -q "^/dev/${MDEV} " /proc/swaps ; then
				swapon /dev/${MDEV}
			fi
			exit 0
		fi
		# check for full-disk partition
		if [ "${DEVBASE}" == "${MDEV}" ] ; then
			if [ -d /sys/block/${DEVBASE}/${DEVBASE}1 ] ; then
				# Partition detected, just tell and quit
				notify
				exit 0
			fi
			if [ ! -f /sys/block/${DEVBASE}/size ] ; then
				# No size at all
				exit 0
			fi
			if [ `cat /sys/block/${DEVBASE}/size` == 0 ] ; then
				# empty device, bail out
				exit 0
			fi
		fi
		# first allow fstab to determine the mountpoint
		if ! mount /dev/$MDEV > /dev/null 2>&1 ; then
			# no fstab entry, use automatic mountpoint
			if [ -z "${LABEL}" ] ; then
				REMOVABLE=`cat /sys/block/$DEVBASE/removable`
				if [ "$DRIVER" = "mmcblk" ]; then
					if [ "${REMOVABLE}" -eq "0" ]; then
						DEVICETYPE="mmc"
					else
						DEVICETYPE="SD-card"
					fi
				else
					readlink -fn /sys/block/$DEVBASE/device | grep -qs 'pci\|ahci'
					EXTERNAL=$?
					if [ "${REMOVABLE}" -eq "0" -a $EXTERNAL -eq 0 ] ; then
						# mount the first non-removable internal device on /media/hdd
						DEVICETYPE="hdd"
					else
						MODEL=`cat /sys/block/$DEVBASE/device/model`
						if [ "$MODEL" == "USB CF Reader   " ]; then
							DEVICETYPE="cf"
						elif [ "$MODEL" == "Compact Flash   " ]; then
							DEVICETYPE="cf"
						elif [ "$MODEL" == "USB SD Reader   " ]; then
							DEVICETYPE="mmc1"
						elif [ "$MODEL" == "USB SD  Reader  " ]; then
							DEVICETYPE="mmc1"
						elif [ "$MODEL" == "SD/MMC          " ]; then
							DEVICETYPE="mmc1"
						elif [ "$MODEL" == "USB MS Reader   " ]; then
							DEVICETYPE="mmc1"
						elif [ "$MODEL" == "SM/xD-Picture   " ]; then
							DEVICETYPE="mmc1"
						elif [ "$MODEL" == "USB SM Reader   " ]; then
							DEVICETYPE="mmc1"
						elif [ "$MODEL" == "MS/MS-Pro       " ]; then
							DEVICETYPE="mmc1"
						else
							DEVICETYPE="usb"
						fi
					fi
				fi
			else
				DEVICETYPE="${LABEL}"
			fi
			# Use mkdir as 'atomic' action, failure means someone beat us to the punch
			MOUNTPOINT="/media/$DEVICETYPE"
			if ! mkdir "${MOUNTPOINT}" ; then
				MOUNTPOINT="/media/$MDEV"
				mkdir -p "${MOUNTPOINT}"
			fi
			if ! mount -t auto /dev/$MDEV "${MOUNTPOINT}" ; then
				rmdir "${MOUNTPOINT}"
			fi
		fi
		;;
	remove)
		MOUNTPOINT=`grep "^/dev/$MDEV\s" /proc/mounts | cut -d' ' -f 2 | sed 's/\\\\040/ /g'`
		if [ -z "${MOUNTPOINT}" ] ; then
			MOUNTPOINT="/media/$MDEV"
		fi
		umount "${MOUNTPOINT}" || umount "/dev/${MDEV}"
		rmdir "${MOUNTPOINT}"
		;;
	*)
		# Unexpected keyword
		exit 1
		;;
esac

notify
