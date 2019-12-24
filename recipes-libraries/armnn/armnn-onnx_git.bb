SUMMARY = "ONNX protobuf files - used in ARMNN for Caffe network models"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=efff5c5110f124a1e2163814067b16e7"

BRANCH = "rel-1.6.1"
PV = "1.6.1"

SRC_URI = "git://github.com/onnx/onnx.git;branch=${BRANCH}"

SRCREV = "1facb4c1bb9cc2107d4dbaf9fd647fefdbbeb0ab"

DEPENDS = "protobuf-native"

inherit cmake

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "-DPYTHON_EXECUTABLE=${HOSTTOOLS_DIR}/python3"

do_install() {
    install -d ${D}${datadir}/${BPN}/onnx/
    for file in ${B}/onnx/*
    do
        install -m 0644 $file ${D}${datadir}/${BPN}/onnx/
    done

}
