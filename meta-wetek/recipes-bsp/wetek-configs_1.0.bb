SUMMARY = "Wetek specific config files"
DESCRIPTION = "Provides set-up for key translations"
SECTION = "base"
LICENSE = "GPLv2"

require conf/license/license-gplv2.inc

PR = "r1"

#INHIBIT_DEFAULT_DEPS = "1"

inherit pkgconfig allarch

S = "${WORKDIR}"

SRC_URI = " \
    file://keytranslation.xml \
"

FILES_${PN} += "${datadir}/enigma2"

# COMPATIBLE_MACHINE = "(wetekplay)"

do_install() {
    install -d ${D}/usr/share/enigma2
    install -m 0644 ${WORKDIR}/keytranslation.xml  ${D}/${datadir}/enigma2
}

