require conf/license/openpli-gplv2.inc

inherit image

IMAGE_INSTALL = " \
	${ROOTFS_PKGMANAGE} \
	3rd-party-feed-configs \
	openpli-feed \
	openpli-settings \
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
		mv ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/bre2ze-neu.png ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/bre2ze.png
		mv ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/bre2ze-neu.xml ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/bre2ze.xml
		mv ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/red1-neu.png ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/red1.png
		mv ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/red1-neu.xml ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/red1.xml
		mv ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/sf108-neu.png ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/sf108.png
		mv ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/sf108-neu.xml ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/sf108.xml
		mv ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/sf3038-neu.png ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/sf108.png
		mv ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/sf3038-neu.xml ${IMAGE_ROOTFS}/usr/share/enigma2/rc_models/sf3038.xml
	cd

	cd ${IMAGE_ROOTFS}/etc/

	if [ "${MACHINE}" = "7100s" ]; then
		mv ${IMAGE_ROOTFS}/etc/model-7100s ${IMAGE_ROOTFS}/etc/model
		rm -r ${IMAGE_ROOTFS}/etc/model-sf3038
		rm -r ${IMAGE_ROOTFS}/etc/model-sf108
		rm -r ${IMAGE_ROOTFS}/etc/model-bre2ze
	fi
	if [ "${MACHINE}" = "ew7362" ]; then
		mv ${IMAGE_ROOTFS}/etc/model-bre2ze ${IMAGE_ROOTFS}/etc/model
		rm -r ${IMAGE_ROOTFS}/etc/model-sf3038
		rm -r ${IMAGE_ROOTFS}/etc/model-sf108
		rm -r ${IMAGE_ROOTFS}/etc/model-7100s
	fi
	if [ "${MACHINE}" = "vg5000" ]; then
		mv ${IMAGE_ROOTFS}/etc/model-sf108 ${IMAGE_ROOTFS}/etc/model
		rm -r ${IMAGE_ROOTFS}/etc/model-bre2ze
		rm -r ${IMAGE_ROOTFS}/etc/model-sf3038
		rm -r ${IMAGE_ROOTFS}/etc/model-7100s
	fi
	if [ "${MACHINE}" = "g300" ]; then
		mv ${IMAGE_ROOTFS}/etc/model-sf3038 ${IMAGE_ROOTFS}/etc/model
		rm -r ${IMAGE_ROOTFS}/etc/model-bre2ze
		rm -r ${IMAGE_ROOTFS}/etc/model-sf108
		rm -r ${IMAGE_ROOTFS}/etc/model-7100s
	fi
	cd

	cd ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/
	if [ "${MACHINE}" = "7100s" ]; then
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/7100s-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/7100s.jpg
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/ew7362-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/ew7362.jpg
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf108-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf108.jpg
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf108-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf3038.jpg
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/ew7362.jpg
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf108.jpg
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf3038.jpg
	fi
	if [ "${MACHINE}" = "ew7362" ]; then
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/7100s-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/7100s.jpg
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/ew7362-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/ew7362.jpg
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf108-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf108.jpg
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf108-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf3038.jpg
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/7100s.jpg
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf108.jpg
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf3038.jpg
	fi
	if [ "${MACHINE}" = "vg5000" ]; then
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf108-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf108.jpg
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf108-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf3038.jpg
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/7100s-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/7100s.jpg
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/ew7362-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/ew7362.jpg
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/ew7362.jpg
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/7100s.jpg
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf3038.jpg
	fi
	if [ "${MACHINE}" = "g300" ]; then
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf108-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf108.jpg
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf108-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf3038.jpg
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/7100s-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/7100s.jpg
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/ew7362-neu.jpg ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/ew7362.jpg
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/ew7362.jpg
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/7100s.jpg
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/sf108.jpg
	fi
	cd
	cd ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/
	if [ "${MACHINE}" = "7100s" ]; then
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/7100s-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/7100s.png
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/ew7362-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/ew7362.png
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/ew7362.png
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf108-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf108.png
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf3038-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/3038.png
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/ew7362.png
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf108.png
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf3038.png
	fi
	if [ "${MACHINE}" = "ew7362" ]; then
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/7100s-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/7100s.png
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/ew7362-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/ew7362.png
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/ew7362.png
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf108-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf108.png
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf3038-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/3038.png
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/7100s.png
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf108.png
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf3038.png
	fi
	if [ "${MACHINE}" = "vg5000" ]; then
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/7100s-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/7100s.png
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/ew7362-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/ew7362.png
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/ew7362.png
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf108-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf108.png
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf3038-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/3038.png
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/7100s.png
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/ew7362.png
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf3038.png
	fi
	if [ "${MACHINE}" = "g300" ]; then
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/7100s-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/7100s.png
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/ew7362-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/ew7362.png
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/ew7362.png
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf108-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf108.png
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf3038-neu.png ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/3038.png
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/7100s.png
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/ew7362.png
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/sf108.png
	fi
	cd
	cd ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/
	if [ "${MACHINE}" = "7100s" ]; then
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/7100s-neu.html ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/7100s.html
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/ew7362-neu.html ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/ew7362.html
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf108-neu.html ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf108.html
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf308-neu.html ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf3038.html
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/ew7362.html
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf108.html
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf3038.html
	fi
	if [ "${MACHINE}" = "ew7362" ]; then
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/7100s-neu.html ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/7100s.html
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/ew7362-neu.html ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/ew7362.html
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf108-neu.html ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf108.html
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf308-neu.html ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf3038.html
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/7100s.html
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf108.html
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf3038.html
	fi
	if [ "${MACHINE}" = "vg5000" ]; then
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/7100s-neu.html ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/7100s.html
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/ew7362-neu.html ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/ew7362.html
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf108-neu.html ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf108.html
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf308-neu.html ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf3038.html
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/7100s.html
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/ew7362.html
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf3038.html
	fi
	if [ "${MACHINE}" = "g300" ]; then
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/7100s-neu.html ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/7100s.html
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/ew7362-neu.html ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/ew7362.html
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf108-neu.html ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf108.html
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf308-neu.html ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf3038.html
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/7100s.html
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/ew7362.html
		rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/sf108.html
	fi
	cd
	cd ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/controllers/models/
		rm -r ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/controllers/models/owibranding.py
		mv ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/controllers/models/owibranding-neu.py ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/controllers/models/owibranding.py
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
