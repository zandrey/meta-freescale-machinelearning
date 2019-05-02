
SRCREV_opencv = "c9ad5779f2803dcc91a9938142209128d30b22d1"

OPENCV_SRC = "git://github.com/opencv/opencv.git;protocol=https"
SRCBRANCH = "master"

SRC_URI_append = " file://enable_cxx_extensions.patch"
SRC_URI_append = " file://fix-openvx-ivx-CV_USERTYPE.patch"
SRC_URI_append = " file://fix-median-blur-namespace.patch"
SRC_URI_append = " file://add-openvx-libraries-to-openx-samples.patch"

PACKAGECONFIG[openmp] = "-DWITH_OPENMP=ON,-DWITH_OPENMP=OFF,,"
PACKAGECONFIG[test] = "-DBUILD_TESTS=ON -DBUILD_PERF_TESTS=ON -DINSTALL_TESTS=ON -DOPENCV_TEST_DATA_PATH=${S}/../extra/testdata, -DBUILD_TESTS=OFF -DBUILD_PERF_TESTS=OFF -DINSTALL_TESTS=OFF,"
PACKAGECONFIG[samples] = "-DBUILD_EXAMPLES=ON -DINSTALL_C_EXAMPLES=ON -DINSTALL_PYTHON_EXAMPLES=ON,-DBUILD_EXAMPLES=OFF,,"

PACKAGECONFIG[trace] = "-DCV_TRACE=ON,-DCV_TRACE=OFF"
PACKAGECONFIG[profiling] = "-DENABLE_PROFILING=ON,-DENABLE_PROFILING=OFF"
PACKAGECONFIG[fastmath] = "-DENABLE_FAST_MATH=ON,-DENABLE_FAST_MATH=OFF"
