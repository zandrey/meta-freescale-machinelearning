SUMMARY = "Tensorflow protobuf files - used in ARMNN for Tensorflow network models"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=01e86893010a1b87e69a213faa753ebd"

SRC_URI = " \
    git://github.com/tensorflow/tensorflow.git;name=tensorflow;branch=r1.11 \
    git://github.com/ARM-software/armnn.git;name=armnn;subdir=${WORKDIR}/armnn;destsuffix=armnn \
"

PV = "1.11.0"

SRCREV_tensorflow = "c19e29306ce1777456b2dbb3a14f511edf7883a8"
SRCREV_armnn = "c577f2c6a3b4ddb6ba87a882723c53a248afbeba"
SRCREV_FORMAT = "tensorflow"

DEPENDS = "protobuf-native"

S = "${WORKDIR}/git"

do_install() {
    # Install TF sources + build artifacts as reuired by ARMNN
    install -d ${D}${datadir}/${BPN}

    # Convert protobuf sources to C sources and install
    ${WORKDIR}/armnn/scripts/generate_tensorflow_protobuf.sh ${D}${datadir}/${BPN} ${STAGING_DIR_NATIVE}${prefix}

    # Install TF Lite sources as reuired by ARMNN
    install -d ${D}${datadir}/${BPN}-lite
    for file in ${S}/tensorflow/contrib/lite/schema/*
    do
        [ -f $file ] && install -m 0644 $file ${D}${datadir}/${BPN}-lite
    done

}

FILES_${PN} += "${datadir}"