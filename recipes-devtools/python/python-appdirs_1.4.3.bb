SUMMARY = "A small Python module for determining appropriate platform-specific dirs, e.g. a "user data dir"."
HOMEPAGE = "http://github.com/ActiveState/appdirs"

# NOTE: License in setup.py/PKGINFO is: MIT
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=31625363c45eb0c67c630a2f73e438e4"

SRC_URI = "https://files.pythonhosted.org/packages/48/69/d87c60746b393309ca30761f8e2b49473d43450b150cb08f3c6df5c11be5/appdirs-${PV}.tar.gz"
SRC_URI[md5sum] = "44c679904082a2133f5566c8a0d3ab42"
SRC_URI[sha256sum] = "9e5896d1372858f8dd3344faf4e5014d21849c756c8d5701f78f8a103b372d92"

S = "${WORKDIR}/appdirs-${PV}"

inherit setuptools

RDEPENDS_${PN} += "python-core python-ctypes python-lang"
