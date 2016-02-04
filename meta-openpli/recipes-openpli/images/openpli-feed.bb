DESCRIPTION = "Openpli-feed files"
LICENSE = "GPL2"

require conf/license/license-gplv2.inc

PACKAGE_ARCH := "${MACHINE_ARCH}"

SRCREV = "${AUTOREV}"

SRC_URI = "file://*"

FILES_${PN} = "/etc/opkg/* /usr/lib/enigma2/python/Plugins/Extensions/BackupSuite/*"
S = "${WORKDIR}"

do_install() {
	    
    install -d ${D}/etc/opkg
    for f in 7100s-feed.conf all-feed.conf bre2ze-feed.conf mips32el-feed.conf
    do
        install -m 755 ${f} ${D}/etc/opkg/${f}
    done

    install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/BackupSuite
    for f in lookuptable-neu.txt
    do
        install -m 755 ${f} ${D}/usr/lib/enigma2/python/Plugins/Extensions/BackupSuite/${f}
    done
}
