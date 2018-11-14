SUMMARY = "Tensorflow Lite files - used in ARMNN for Tensorflow network models"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=01e86893010a1b87e69a213faa753ebd"

SRC_URI = "git://github.com/tensorflow/tensorflow.git"

PV = "1.11.0"

SRCREV = "c19e29306ce1777456b2dbb3a14f511edf7883a8"

DEPENDS = "flatbuffers"

S = "${WORKDIR}/git"

do_install() {
    # Install sources as reuired by ARMNN
    install -d ${D}${datadir}/${BPN}
    for file in ${S}/tensorflow/contrib/lite/schema/*
    do
        [ -f $file ] && install -m 0644 $file ${D}${datadir}/${BPN}
    done
}
