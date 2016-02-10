# We know about rtc
include conf/machine/include/build-extras.inc

MACHINE_FEATURES += "colorlcd220 lcd220 "

MACHINE_EXTRA_RRECOMMENDS += " \
    ceryon-lcd \
"

MACHINE_EXTRA_RDEPENDS = "\
    ${@base_contains('MACHINEBUILD', 'twinboxlcd', 'rt3070 firmware-rt2870' , '', d)} \
    ${@base_contains('MACHINEBUILD', 'twinboxlcd', 'rt3070 firmware-rt3070' , '', d)} \
    "

BRAND_OEM = "ceryon"

MACHINE_NAME = "\
${@base_contains('MACHINEBUILD', 'twinboxlcd', 'TWINBOX LCD' , '', d)}\
${@base_contains('MACHINEBUILD', 'mbmicro', 'Premium Micro' , '', d)}\
${@base_contains('MACHINEBUILD', 'odin2hybrid', 'ODIN2' , '', d)}\
${@base_contains('MACHINEBUILD', 'odinplus', 'ODIN2' , '', d)}\
${@base_contains('MACHINEBUILD', 'e4hd', 'E4HD' , '', d)}\
${@base_contains('MACHINEBUILD', 'e4hdhybrid', 'E4HD HYBRID' , '', d)}\
"

MACHINE_BRAND = "\
${@base_contains('MACHINEBUILD', 'twinboxlcd', 'RED EAGLE' , '', d)}\
${@base_contains('MACHINEBUILD', 'mbmicro', 'Miraclebox' , '', d)}\
${@base_contains('MACHINEBUILD', 'odin2hybrid', 'Opticum' , '', d)}\
${@base_contains('MACHINEBUILD', 'odinplus', 'Opticum' , '', d)}\
${@base_contains('MACHINEBUILD', 'e4hd', 'AXAS' , '', d)}\
${@base_contains('MACHINEBUILD', 'e4hdhybrid', 'AXAS' , '', d)}\
"

IMAGEDIR ?= "\
${@base_contains('MACHINEBUILD', 'twinboxlcd', 'redeagle/twinboxlcd' , '', d)}\
${@base_contains('MACHINEBUILD', 'mbmicro', 'miraclebox/micro' , '', d)}\
${@base_contains('MACHINEBUILD', 'odin2hybrid', 'odin2/hybrid' , '', d)}\
${@base_contains('MACHINEBUILD', 'odinplus', 'odinplus' , '', d)}\
${@base_contains('MACHINEBUILD', 'e4hd', 'e4hd' , '', d)}\
${@base_contains('MACHINEBUILD', 'e4hdhybrid', 'e4hd' , '', d)}\
"

MTD_KERNEL = "mtd1"
MTD_ROOTFS = "mtd2"
KERNEL_FILE = "kernel.bin"
ROOTFS_FILE = "rootfs.bin"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS = "\
    ceryon-dvb-modules-${MACHINE} \
    "

DVBPROVIDER = "kernel"
DVBSKYPROVIDER = "kernel"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS = "\
    kernel-module-cdfs \
    "

MACHINE_EXTRA_RRECOMMENDS = " \
    ceryon-shutdown \
    gst-plugin-dvbmediasink \
    "

include conf/machine/include/autoload-filesystems.inc

DVBMEDIASINK_CONFIG = "--with-wma --with-wmv --with-pcm --with-dts --with-eac3"

TARGET_ARCH = "mipsel"
DEFAULTTUNE = "mips32el"

PREFERRED_PROVIDER_virtual/kernel = "linux-ceryon"

PREFERRED_VERSION_linux-ceryon = "4.0.1"
PREFERRED_VERSION_linux-libc-headers = "4.0"

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
   ${@base_contains("MACHINE_FEATURES", "lcd220", "cp ${DEPLOY_DIR_IMAGE}/7100s/lcdsplash220.bin ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/lcdsplash.bin;" , "", d)} \
   ${@base_contains("MACHINE_FEATURES", "lcd220", "cp ${DEPLOY_DIR_IMAGE}/7100s/lcdwarning220.bin ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/lcdwarning.bin;" , "", d)} \
   ${@base_contains("MACHINE_FEATURES", "lcd220", "cp ${DEPLOY_DIR_IMAGE}/7100s/lcdwaitkey220.bin ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/lcdwaitkey.bin;" , "", d)} \
   ${@base_contains("MACHINE_FEATURES", "lcd220", "cp ${DEPLOY_DIR_IMAGE}/7100s/lcdcomplete220.bin ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/lcdcomplete.bin;" , "", d)} \
   cp ${DEPLOY_DIR_IMAGE}/7100s/splash.bin ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/splash.bin; \
   cp ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ubi ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/rootfs.bin; \
   gzip -9c ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}.bin > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/kernel.bin; \
   echo ${DISTRO_NAME}-${DISTRO_VERSION}-${DATE} > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/imageversion; \
   echo "rename this file to 'force' to force an update without confirmation" > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/noforce; \
   cd ${DEPLOY_DIR_IMAGE}; \
   zip ${DISTRO_NAME}-${DISTRO_VERSION}-${MACHINE}_usb.zip ${IMAGEDIR}/*; \
   rm -Rf ${IMAGEDIR}; \
	"

MACHINE_FEATURES += "3dtv alsa hdmicec switchoff bootsplash omb videoenhancement directfb sdl skins1080 extrakernelwifi wlan \
    ${@base_contains('MACHINEBUILD', 'e4hdhybrid', 'dvb-c dvb-c-only' , '', d)} \
"

BOOTLOGO_FILENAME = "splash.bin"

require conf/machine/include/tune-mips32.inc
require conf/machine/include/ceryon-wifi.inc
# add support for extra feeds
PACKAGE_ARCHS += " ${MACHINEBUILD}"
