# Adapted from linux-imx.inc, copyright (C) 2013 O.S. Systems Software LTDA
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-imx.inc

DESCRIPTION = "Linux kernel for MarS Board"

SRC_URI = "git://github.com/silmerusse/linux-imx.git;branch=silmerusse \
file://defconfig"
SRCREV = "${AUTOREV}"

COMPATIBLE_MACHINE = "(marsboard)"

# Move .debug to a seperate package to avoid QA issue.
PACKAGES =+ "kernel-dbg"
FILES_kernel-dbg = "${KERNEL_SRC_PATH}/drivers/input/touchscreen/generic_ts_rel/lib/.debug/"

