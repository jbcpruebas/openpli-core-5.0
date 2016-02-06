DESCRIPTION = "Openpli-feed files"
LICENSE = "GPL2"

require conf/license/license-gplv2.inc

PACKAGE_ARCH := "${MACHINE_ARCH}"

SRCREV = "${AUTOREV}"

SRC_URI = "file://*"

FILES_${PN} = "/etc/opkg/* /usr/lib/enigma2/python/Plugins/Extensions/BackupSuite/* /usr/share/enigma2/rc_models* /usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes*  /usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes*"
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

    install -d ${D}/usr/share/enigma2/rc_models
    for f in bre2ze.png bre2ze.xml red1.png red1.xml rc_models-neu.cfg
    do
        install -m 755 ${f} ${D}/usr/share/enigma2/rc_models/${f}
    done

    install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes
    for f in twinboxlcd-neu.jpg ultimo-neu.jpg
    do
        install -m 755 ${f} ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/${f}
    done

    install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes
    for f in twinbox-neu.png vu_ultimo-neu.png
    do
        install -m 755 ${f} ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/${f}
    done
}
