SUMMARY = "Memory Efficient Serialization Library"
HOMEPAGE = "https://github.com/google/flatbuffers"
SECTION = "console/tools"
LICENSE = "Apache-2.0"

#PACKAGE_BEFORE_PN = "${BPN}-native"
DEPENDS += " ${BPN}-native"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a873c5645c184d51e0f9b34e1d7cf559"

SRCREV = "20a400e940634108bad812f571582eeffc728eac"

SRC_URI = "git://github.com/google/flatbuffers.git"

# Make sure C++11 is used, required for example for GCC 4.9
CXXFLAGS += "-std=c++11"
BUILD_CXXFLAGS += "-std=c++11"

EXTRA_OECMAKE += "\
    -DFLATBUFFERS_BUILD_TESTS=ON \
    -DFLATBUFFERS_BUILD_SHAREDLIB=ON \
    -DFLATBUFFERS_FLATC_EXECUTABLE="${STAGING_BINDIR_NATIVE}/flatc" \
"

inherit cmake

S = "${WORKDIR}/git"

do_install_append() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/flatc ${D}${bindir}
}

FILES_${PN} += "${libdir}"

#BBCLASSEXTEND = "native nativesdk"