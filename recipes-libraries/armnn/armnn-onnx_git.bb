SUMMARY = "ONNX protobuf files - used in ARMNN for Caffe network models"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=efff5c5110f124a1e2163814067b16e7"

BRANCH = "rel-1.6.0"
PV = "1.6.0"

SRC_URI = " \
	git://github.com/onnx/onnx.git;branch=${BRANCH} \
	file://0001-CMakeLists-do-not-add-ONNX_API-for-GCC-build.patch \
"

SRCREV = "553df22c67bee5f0fe6599cff60f1afc6748c635"

DEPENDS = "protobuf-native"

inherit cmake

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "-DPYTHON_EXECUTABLE=${HOSTTOOLS_DIR}/python3 -DONNX_VERIFY_PROTO3=ON -DONNX_ML=OFF"

# Override the install task, since we are not interested in any of the libraries
# delivered from the build and rather require to provide a Protobuf sources
do_install() {
    install -d ${D}${datadir}/${BPN}/onnx/
    for file in ${B}/onnx/*
    do
        if [ -f $file ]; then
            install -m 0644 $file ${D}${datadir}/${BPN}/onnx/
        fi
    done

}

