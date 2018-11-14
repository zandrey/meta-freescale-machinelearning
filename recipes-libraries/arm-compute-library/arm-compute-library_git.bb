# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# NOTE: multiple licenses have been detected; if that is correct you should separate
# these in the LICENSE value using & if the multiple licenses all apply, or | if there
# is a choice between the multiple licenses. If in doubt, check the accompanying
# documentation to determine which situation is applicable.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=762a7ba8d2ddc3b38d88742fbaf0b62d \
                    file://include/half/LICENSE.txt;md5=fe7e5a4795c76b317919afd2d3da5983"

SRC_URI = "git://github.com/ARM-software/ComputeLibrary.git;protocol=https"

# Modify these as desired
PV = "1808"
SRCREV = "52ba29e936b8e711e8acdfe819e36f884d4f3fe1"

S = "${WORKDIR}/git"

inherit scons

PACKAGECONFIG ??= ""
PACKAGECONFIG[benchmark] = "benchmark_tests=1,benchmark_tests=0"
PACKAGECONFIG[validation] = "validation_tests=1,validation_tests=0"
PACKAGECONFIG[opencl] = "opencl=1,opencl=0"

# Specify any options you want to pass to scons using EXTRA_OESCONS:
EXTRA_OESCONS = "${PARALLEL_MAKE} extra_cxx_flags='-fPIC' build=native ${PACKAGECONFIG_CONFARGS}"
EXTRA_OESCONS += "${@bb.utils.contains('TARGET_ARCH', 'aarch64', 'arch=arm64-v8a neon=1', '', d)}"

do_install() {
	CP_ARGS="-Prf --preserve=mode,timestamps --no-preserve=ownership"
	install -d ${D}${includedir}
	
	# using cp instead of install - just temporary thing
	cp $CP_ARGS arm_compute ${D}${includedir}
	cp $CP_ARGS support ${D}${includedir}
	cp $CP_ARGS include/half ${D}${includedir}

	install -d ${D}${libdir}
	install -m 0755 build/libarm_compute*.so ${D}${libdir}

	#prepare exports for ARM NN
	install -d ${D}${datadir}/${BPN}
	#documentation folder is causing issues during write_rpm phase
	#cp $CP_ARGS ${S}/. ${D}${datadir}/${BPN}
	rsync -av ${S}/. ${D}${datadir}/${BPN} --exclude .git --exclude documentation
}

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

PACKAGES = "${PN} ${PN}-source"

FILES_${PN} += "${includedir}/* ${libdir}/*"
FILES_${PN}-source = "${datadir}/${BPN}"

# Suppress the QA error
# usage of rsync is causing host-user-contaminated error
INSANE_SKIP_${PN} += "ldflags  libdir staticdev host-user-contaminated"
INSANE_SKIP_${PN}-source += "ldflags  libdir staticdev host-user-contaminated"


# We support i.MX8 only (for now)
COMPATIBLE_MACHINE = "(mx8)"
