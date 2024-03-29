# We know about rtc
include conf/machine/include/build-extras.inc

PREFERRED_VERSION_linux-entwopia = "3.14.2"
PREFERRED_VERSION_linux-libc-headers = "3.13"

BRAND_OEM = "entwopia"

MACHINE_NAME = "\
${@base_contains('MACHINEBUILD', 'enfinity', 'ENfinity' , '', d)}\
${@base_contains('MACHINEBUILD', 'marvel1', 'Marvel 1' , '', d)}\
${@base_contains('MACHINEBUILD', 'x2plus', 'enfinityX 2plus' , '', d)}\
${@base_contains('MACHINEBUILD', 'bre2ze', 'BRE2ZE' , '', d)}\
${@base_contains('MACHINEBUILD', 'evomini', 'ENfinity mini' , '', d)}\
"

MACHINE_BRAND = "\
${@base_contains('MACHINEBUILD', 'enfinity', 'EVO' , '', d)}\
${@base_contains('MACHINEBUILD', 'marvel1', 'VisionNet' , '', d)}\
${@base_contains('MACHINEBUILD', 'x2plus', 'EVO' , '', d)}\
${@base_contains('MACHINEBUILD', 'bre2ze', 'WWIO' , '', d)}\
${@base_contains('MACHINEBUILD', 'evomini', 'EVO' , '', d)}\
"

IMAGEDIR ?= "\
${@base_contains('MACHINEBUILD', 'enfinity', 'enfinity' , '', d)}\
${@base_contains('MACHINEBUILD', 'marvel1', 'marvel1' , '', d)}\
${@base_contains('MACHINEBUILD', 'x2plus', 'x2plus' , '', d)}\
${@base_contains('MACHINEBUILD', 'bre2ze', 'bre2ze' , '', d)}\
${@base_contains('MACHINEBUILD', 'evomini', 'mini' , '', d)}\
"

MTD_ROOTFS = "mtd0"
MTD_KERNEL = "mtd2"
KERNEL_FILE = "kernel.bin"
ROOTFS_FILE = "rootfs.bin"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS = "\
    entwopia-dvb-modules-${MACHINE} \
    "

DVBPROVIDER = "kernel"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS = "\
    kernel-module-cdfs \
    "

MACHINE_EXTRA_RRECOMMENDS = " \
    gstreamer1.0-plugin-dvbmediasink \
    "

include conf/machine/include/autoload-filesystems.inc

DVBMEDIASINK_CONFIG = "--with-wma --with-wmv --with-pcm --with-dts --with-eac3"

TARGET_ARCH = "mipsel"
DEFAULTTUNE = "mips32el"

GLIBC_EXTRA_OECONF = "--with-tls --with-__thread"

PREFERRED_PROVIDER_virtual/kernel = "linux-entwopia"

KERNEL_IMAGETYPE = "vmlinux"
KERNEL_OUTPUT = "${KERNEL_IMAGETYPE}"
KERNEL_CONSOLE = "ttyS0,115200n8"

IMAGE_FSTYPES ?= "ubi"

UBI_VOLNAME = "rootfs"
MKUBIFS_ARGS = "-m 2048 -e 126976 -c 4096"
UBINIZE_ARGS = "-m 2048 -p 128KiB"

IMAGE_CMD_ubi_prepend = " \
    rm -rf ${IMAGE_ROOTFS}/boot/*; \
    rm -rf ${IMAGE_ROOTFS}/tmp/*; \
    "

IMAGE_CMD_ubi_append = " \
   mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
   cp ${DEPLOY_DIR_IMAGE}/wwio/splash.bin ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/splash.bin; \
   cp ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ubi ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/rootfs.bin; \
   gzip -9c ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}.bin > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/kernel.bin; \
   echo ${DISTRO_NAME}-${DISTRO_VERSION}-${DATE} > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/imageversion; \
   echo "rename this file to 'force' to force an update without confirmation" > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/noforce; \
   cd ${DEPLOY_DIR_IMAGE}; \
   zip ${DISTRO_NAME}-${DISTRO_VERSION}-${MACHINE}_usb.zip ${IMAGEDIR}/*; \
   rm -Rf ${IMAGEDIR}; \
	"


MACHINE_FEATURES += "3dtv alsa hdmicec switchoff bootsplash omb skins1080 videoenhancement ci externalwifi kernelwifi wlan"

BOOTLOGO_FILENAME = "splash.bin"

require conf/machine/include/tune-mips32.inc
require conf/machine/include/entwopia-wifi.inc
# add support for extra feeds
PACKAGE_ARCHS += " ${MACHINEBUILD}"
