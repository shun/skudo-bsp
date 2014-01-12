# Base this image on generic IVI image
inherit core-image

IMAGE_FEATURES += "debug-tweaks"
DISTRO_FEATURES += "pulseaudio"
WEB = "web-webkit"

SOC_EXTRA_IMAGE_FEATURES ?= "tools-testapps"

# Add extra image features
EXTRA_IMAGE_FEATURES += " \
    ${SOC_EXTRA_IMAGE_FEATURES} \
    tools-debug \
    tools-profile \
"

SOC_IMAGE_INSTALL = ""
SOC_IMAGE_INSTALL_mx5 = "glcubes-demo"

IMAGE_INSTALL += "${SOC_IMAGE_INSTALL}"
IMAGE_INSTALL += "cpufrequtils"
IMAGE_INSTALL += "packagegroup-fsl-gstreamer "
IMAGE_INSTALL += "packagegroup-fsl-tools-testapps"
IMAGE_INSTALL += "packagegroup-fsl-tools-benchmark"
#IMAGE_INSTALL += "packagegroup-qt-in-use-demos"
#IMAGE_INSTALL += "qt4-plugin-phonon-backend-gstreamer"

export IMAGE_BASENAME = "skudo-image"

