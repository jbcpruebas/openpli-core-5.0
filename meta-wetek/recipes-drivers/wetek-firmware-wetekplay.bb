DESCRIPTION = "Hardware firmware for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

PACKAGE_ARCH = "all"

SRC_URI = "file://firmware-avl6211.zip \
           file://firmware-mn88436.zip \
          "


S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"
PACKAGES = "${PN}"
FILES_${PN} += "${base_libdir}/firmware"


do_compile() {
}

do_install() {
	install -d ${D}/${base_libdir}/firmware
        cd ${WORKDIR}
	#for i in *.fw *.bin *.obj ; do
	for i in *.fw ; do
		install -m 0644 $i ${D}/${base_libdir}/firmware
	done
}
SRC_URI[md5sum] = "45f55bfec9954ea26923ad442fb3336b"
SRC_URI[sha256sum] = "c47ba63cce9527c668efe45b86aa5d5aa84c475bc9debce07f18e362c578f382"

