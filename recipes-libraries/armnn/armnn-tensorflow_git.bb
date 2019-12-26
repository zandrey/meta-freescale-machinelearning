SUMMARY = "Tensorflow protobuf files - used in ARMNN for Tensorflow network models"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=64a34301f8e355f57ec992c2af3e5157"

SRC_URI = " \
    git://github.com/tensorflow/tensorflow.git;name=tensorflow;branch=r2.0\
    git://github.com/ARM-software/armnn.git;name=armnn;subdir=${WORKDIR}/armnn;destsuffix=armnn \
"

PV = "2.0.0"

SRCREV_tensorflow = "64c3d382cadf7bbe8e7e99884bede8284ff67f56"
SRCREV_armnn = "c577f2c6a3b4ddb6ba87a882723c53a248afbeba"
SRCREV_FORMAT = "tensorflow"

DEPENDS = "protobuf-native"

S = "${WORKDIR}/git"

do_install() {
    # Install TF sources + build artifacts as reuired by ARMNN
    install -d ${D}${datadir}/${BPN}

    # Convert protobuf sources to C sources and install
    bbnote "Generate TF Protobuf files..."
    ${WORKDIR}/armnn/scripts/generate_tensorflow_protobuf.sh ${D}${datadir}/${BPN} ${STAGING_DIR_NATIVE}${prefix}

    # Install TF Lite sources as required by ARMNN
    bbnote "Install TF Lite sources as required by ARMNN"
    install -d ${D}${datadir}/${BPN}-lite

    for file in ${S}/tensorflow/lite/schema/*
    do
        [ -f $file ] && install -m 0644 $file ${D}${datadir}/${BPN}-lite
    done
    bbnote "Installation completed"
}

FILES_${PN} += "${datadir}"
