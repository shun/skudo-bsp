# Copyright (C) 2011, 2012 Freescale
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "GPU driver and apps for x11 on mx51"
LICENSE = "Proprietary"
SECTION = "libs"
PR = "r13"

# FIXME: Replace for correct AMD license
LIC_FILES_CHKSUM = "file://usr/include/VG/openvg.h;endline=30;md5=b0109611dd76961057d4c45ae6519802"
DEPENDS = "virtual/libx11 libxrender"

PROVIDES = "virtual/egl virtual/libgles1 virtual/libgles2"

SRC_URI = "${FSL_MIRROR}/amd-gpu-x11-bin-mx51-${PV}.bin;fsl-eula=true \
           file://fix-linux-build-check.patch"
SRC_URI[md5sum] = "54391a4e670b597d06d01253fb217cad"
SRC_URI[sha256sum] = "c7a6fa03b7aa2a375556c59908876554ba720c1e744baba2debb84a408f790db"

inherit fsl-eula-unpack

# FIXME: All binaries lack GNU_HASH in elf binary but as we don't have
# the source we cannot fix it. Disable the insane check for now.
python populate_packages_prepend() {
    for p in d.getVar('PACKAGES', True).split():
        d.setVar("INSANE_SKIP_%s" % p, "ldflags")
}

do_install () {
    install -d ${D}${libdir}
    install -d ${D}${bindir}
    install -d ${D}${includedir}

    cp -axr ${S}/usr/bin/* ${D}${bindir}
    cp -axf ${S}/usr/lib/* ${D}${libdir}
    cp -axr ${S}/usr/include/* ${D}${includedir}

    find ${D}${bindir} -type f -exec chmod 755 {} \;
    find ${D}${libdir} -type f -exec chmod 644 {} \;
    find ${D}${includedir} -type f -exec chmod 644 {} \;

    # FIXME: Fix sonames of broken libraries
    mv ${D}${libdir}/lib2dz160.so ${D}${libdir}/lib2dz160.so.0
    mv ${D}${libdir}/lib2dz430.so ${D}${libdir}/lib2dz430.so.0
    ln -sf lib2dz160.so.0 ${D}${libdir}/lib2dz160.so
    ln -sf lib2dz430.so.0 ${D}${libdir}/lib2dz430.so

    # FIXME: Remove unkown files
    rm -r ${D}${libdir}/libcsi.a \
          ${D}${libdir}/libres.a
}

PACKAGES =+ "libgsl-fsl-mx51 libgsl-fsl-mx51-dev libgsl-fsl-mx51-dbg \
             libegl-mx51 libegl-mx51-dev libegl-mx51-dbg \
             libgles-mx51 libgles-mx51-dev libgles-mx51-dbg \
             libgles2-mx51 libgles2-mx51-dev libgles2-mx51-dbg \
             libopenvg-mx51 libopenvg-mx51-dev libopenvg-mx51-dbg \
             lib2dz160-mx51 lib2dz160-mx51-dbg \
             lib2dz430-mx51 lib2dz430-mx51-dbg"

FILES_${PN}-dbg = "${bindir}/.debug/*"

FILES_libgsl-fsl-mx51 = "${libdir}/libgsl-fsl${SOLIBS}"
FILES_libgsl-fsl-mx51-dev = "${libdir}/libgsl-fsl${SOLIBSDEV}"
FILES_libgsl-fsl-mx51-dbg = "${libdir}/.debug/libgsl-fsl${SOLIBS}"

FILES_libegl-mx51 = "${libdir}/libEGL${SOLIBS}"
FILES_libegl-mx51-dev = "${includedir}/EGL ${includedir}/KHR ${libdir}/libEGL${SOLIBSDEV}"
FILES_libegl-mx51-dbg = "${libdir}/.debug/libEGL${SOLIBS}"

FILES_libgles-mx51 = "${libdir}/libGLESv1*${SOLIBS}"
FILES_libgles-mx51-dev = "${includedir}/GLES ${libdir}/libGLESv1*${SOLIBSDEV}"
FILES_libgles-mx51-dbg = "${libdir}/.debug/libGLESv1*${SOLIBS}"

FILES_libgles2-mx51 = "${libdir}/libGLESv2${SOLIBS}"
FILES_libgles2-mx51-dev = "${includedir}/GLES2 ${libdir}/libGLESv2${SOLIBSDEV}"
FILES_libgles2-mx51-dbg = "${libdir}/.debug/libGLESv2${SOLIBS}"

FILES_libopenvg-mx51 = "${libdir}/libOpenVG${SOLIBS}"
FILES_libopenvg-mx51-dev = "${includedir}/VG ${libdir}/libOpenVG${SOLIBSDEV}"
FILES_libopenvg-mx51-dbg = "${libdir}/.debug/libOpenVG${SOLIBS}"

FILES_lib2dz160-mx51 = "${libdir}/lib2dz160${SOLIBS}"
FILES_lib2dz160-mx51-dbg = "${libdir}/.debug/lib2dz160${SOLIBS}"

FILES_lib2dz430-mx51 = "${libdir}/lib2dz430${SOLIBS}"
FILES_lib2dz430-mx51-dbg = "${libdir}/.debug/lib2dz430${SOLIBS}"

COMPATIBLE_MACHINE = "(mx5)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
