require conf/license/openpli-gplv2.inc

inherit image

IMAGE_INSTALL = " \
	${ROOTFS_PKGMANAGE} \
	3rd-party-feed-configs \
	openpli-feed \
	avahi-daemon \
	dropbear \
	e2fsprogs-e2fsck \
	e2fsprogs-mke2fs \
	e2fsprogs-tune2fs \
	fakelocale \
	kernel-params \
	modutils-loadscript \
	nfs-utils-client \
	openpli-bootlogo \
	opkg \
	packagegroup-base \
	packagegroup-core-boot \
	parted \
	samba-base \
	sdparm \
	tuxbox-common \
	tuxbox-links \
	tzdata \
	volatile-media \
	vsftpd \
	python-argparse \
	"

export IMAGE_BASENAME = "openpli"
IMAGE_LINGUAS = ""

IMAGE_FEATURES += "package-management"


# Remove the mysterious var/lib/opkg/lists that appears to be the result
# of the installer that populates the rootfs. I wanted to call this
# rootfs_remove_opkg_leftovers but that fails to parse.
rootfsremoveopkgleftovers() {
	cd ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/BackupSuite/
		rm -r ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/BackupSuite/lookuptable.txt
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/BackupSuite/lookuptable-neu.txt ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/BackupSuite/lookuptable.txt
	cd

	cd ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/
		rm -r ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/rc_models.cfg
		mv ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/rc_models-neu.cfg ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/rc_models.cfg
	cd

	cd ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/ultimo-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/ultimo.jpg
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/twinboxlcd-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/twinboxlcd.jpg
	cd

	cd ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/twinbox-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/twinbox.png
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/vu_ultimo-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/vu_ultimo.png
	cd

	cd ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/
		rm -r ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/rc_models.cfg
		mv ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/rc_models-neu.cfg ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/rc_models.cfg
	cd

	rm -r ${IMAGE_ROOTFS}/var/lib/opkg/lists
	if [ "${MACHINE}" = "7100s" ]; then
		rm -r ${IMAGE_ROOTFS}/etc/opkg/bre2ze-feed.conf	
		rm -r ${IMAGE_ROOTFS}/etc/opkg/3rd-party-7100s-feed.conf
	fi
	if [ "${MACHINE}" = "ew7362" ]; then
		rm -r ${IMAGE_ROOTFS}/etc/opkg/7100s-feed.conf
		rm -r ${IMAGE_ROOTFS}/etc/opkg/3rd-party-ew7362-feed.conf
	fi
}

# Some features in image.bbclass we do NOT want, so override them
# to be empty. We want to log in as root, but NOT via SSH. So we want
# to live without debug-tweaks...
zap_root_password () {
	true
}
ssh_allow_empty_password () {
	true
}
license_create_manifest() {
}

ROOTFS_POSTPROCESS_COMMAND += "rootfsremoveopkgleftovers;"
