SUMMARY = "Seamless operability between C++11 and Python"
HOMEPAGE = "https://github.com/pybind/pybind11"

# NOTE: License in setup.py/PKGINFO is: BSD
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=beb87117af69fd10fbf9fb14c22a2e62"

SRC_URI = "https://github.com/pybind/pybind11/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "c533a107bc95bd0c5525ff650b11bfa1"
SRC_URI[sha256sum] = "b69e83658513215b8d1443544d0549b7d231b9f201f6fc787a2b2218b408181e"

S = "${WORKDIR}/pybind11-${PV}"

inherit setuptools

RDEPENDS_${PN} += "python-argparse python-core python-distutils"
