SUMMARY = "Additional wetek plugins for Enigma2"
MAINTAINER = "wetek team"
PACKAGE_ARCH = "${MACHINE_ARCH}"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=8e37f34d0e40d32ea2bc90ee812c9131"

FILES_${PN}-meta = "${datadir}/meta"
PACKAGES += "${PN}-meta"

DESCRIPTION_enigma2-plugin-extensions-rcuselect = "Change Remote for Wetek"

inherit autotools-brokensep pythonnative

#SRCREV = "${AUTOREV}"
#PV = "1.0+git${SRCPV}"
#PKGV = "1.0+git${GITPKGV}"
PV = "1.1"
PKGV = "1.0"

SRC_URI = "file://wetek-plugin-rcuselect-20160208.tgz"

S = "${WORKDIR}/RCUSelect"

python populate_packages_prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', '%s', recursive=True, match_path=True, prepend=True, extra_depends="enigma2")
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', '%s (source files)', recursive=True, match_path=True, prepend=True, extra_depends="enigma2")
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.la$', 'enigma2-plugin-%s-dev', '%s (development)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.a$', 'enigma2-plugin-%s-staticdev', '%s (static development)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/(.*/)?\.debug/.*$', 'enigma2-plugin-%s-dbg', '%s (debug)', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\/.*\.po$', 'enigma2-plugin-%s-po', '%s (translations)', recursive=True, match_path=True, prepend=True)
}
