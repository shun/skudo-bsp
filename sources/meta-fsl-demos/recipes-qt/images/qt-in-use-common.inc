SYSTEMD_INSTALL = " \
    systemd \
    systemd-compat-units \
    systemd-speed-hacks \
    udev-systemd \
    rsyslog-systemd \
"

SYSV_INSTALL = " \
    udev \
    sysvinit \
    initscripts \
    tinylogin \
"

COMMON_INSTALL = " \
    base-files \
    base-passwd \
    busybox \
    ${@base_contains("MACHINE_FEATURES", "systemd", "${SYSTEMD_INSTALL}", "${SYSV_INSTALL}", d)} \
    ${QT_NAME}-plugin-phonon-backend-gstreamer \
    packagegroup-fsl-gstreamer \
    packagegroup-base \
"

QT_DEMOS = " \
    ${QT_NAME}-demos \
    ${QT_NAME}-examples \
"

do_rootfs[depends] += "virtual/kernel:do_populate_sysroot"

inherit image
