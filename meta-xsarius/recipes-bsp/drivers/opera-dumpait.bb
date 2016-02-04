DESCRIPTION = "dumpait"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
#LIC_FILES_CHKSUM = "file://LICENSE.GPLv3;md5=5ed852a46d22220a8b07a68e564d84c7"

SRCREV="${AUTOREV}"
PR = "r0"
DESTDIR = "enigma2/python/Plugins/Extensions/HbbTV"

SRC_URI[md5sum] = "5764218db66f2ecd4defee6db62b0651"
SRC_URI[sha256sum] = "022a799c8b7d803536bde73ccf0a0f1148dfa2abb320474c09fe109f6f56238e"

SRC_URI = "http://en3homeftp.net/pub/packages/dumpait.tar.gz"

inherit autotools pkgconfig

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}/usr/lib/${DESTDIR}
    install -m 0755 ${WORKDIR}/dumpait ${D}/usr/lib/${DESTDIR}
}

FILES_${PN} = "${libdir}/${DESTDIR}/dumpait"

PACKAGES = "${PN}"

