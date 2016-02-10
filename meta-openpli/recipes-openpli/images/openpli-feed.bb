DESCRIPTION = "Openpli-feed files"
LICENSE = "GPL2"

require conf/license/license-gplv2.inc

PACKAGE_ARCH := "${MACHINE_ARCH}"

SRCREV = "${AUTOREV}"

SRC_URI = "file://*"

FILES_${PN} = "/etc* /etc/opkg/* /usr/lib/enigma2/python/Plugins/Extensions/BackupSuite/* /usr/share/enigma2/rc_models* /usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes*  /usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes* /usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes* /usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/controllers/models*"
S = "${WORKDIR}"

do_install() {
    install -d ${D}/etc/
    for f in model-7100s model-sf3038
    do
        install -m 755 ${f} ${D}/etc/${f}
    done

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
    for f in bre2ze-neu.png bre2ze-neu.xml red1-neu.png red1-neu.xml rc_models-neu.cfg
    do
        install -m 755 ${f} ${D}/usr/share/enigma2/rc_models/${f}
    done

    install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes
    for f in 7100s-neu.jpg ew7362-neu.jpg
    do
        install -m 755 ${f} ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/boxes/${f}
    done

    install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes
    for f in 7100s-neu.html ew7362-neu.html
    do
        install -m 755 ${f} ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/static/remotes/${f}
    done

    install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes
    for f in 7100s-neu.png ew7362-neu.png
    do
        install -m 755 ${f} ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/public/images/remotes/${f}
    done

    install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/controllers/models
    for f in owibranding-neu.py
    do
        install -m 755 ${f} ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/controllers/models/${f}
    done
}
