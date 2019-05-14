# Install script for directory: /home/quyenpham/Downloads/ImageProcessing/app/src/main/cpp/lbplibrary

# Set the install prefix
if(NOT DEFINED CMAKE_INSTALL_PREFIX)
  set(CMAKE_INSTALL_PREFIX "/usr/local")
endif()
string(REGEX REPLACE "/$" "" CMAKE_INSTALL_PREFIX "${CMAKE_INSTALL_PREFIX}")

# Set the install configuration name.
if(NOT DEFINED CMAKE_INSTALL_CONFIG_NAME)
  if(BUILD_TYPE)
    string(REGEX REPLACE "^[^A-Za-z0-9_]+" ""
           CMAKE_INSTALL_CONFIG_NAME "${BUILD_TYPE}")
  else()
    set(CMAKE_INSTALL_CONFIG_NAME "Debug")
  endif()
  message(STATUS "Install configuration: \"${CMAKE_INSTALL_CONFIG_NAME}\"")
endif()

# Set the component getting installed.
if(NOT CMAKE_INSTALL_COMPONENT)
  if(COMPONENT)
    message(STATUS "Install component: \"${COMPONENT}\"")
    set(CMAKE_INSTALL_COMPONENT "${COMPONENT}")
  else()
    set(CMAKE_INSTALL_COMPONENT)
  endif()
endif()

# Install shared libraries without execute permission?
if(NOT DEFINED CMAKE_INSTALL_SO_NO_EXE)
  set(CMAKE_INSTALL_SO_NO_EXE "1")
endif()

# Is this installation the result of a crosscompile?
if(NOT DEFINED CMAKE_CROSSCOMPILING)
  set(CMAKE_CROSSCOMPILING "TRUE")
endif()

if("x${CMAKE_INSTALL_COMPONENT}x" STREQUAL "xruntimex" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/lib" TYPE SHARED_LIBRARY FILES "/home/quyenpham/Downloads/ImageProcessing/app/build/intermediates/cmake/debug/obj/arm64-v8a/liblbp.so")
  if(EXISTS "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/liblbp.so" AND
     NOT IS_SYMLINK "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/liblbp.so")
    if(CMAKE_INSTALL_DO_STRIP)
      execute_process(COMMAND "/home/quyenpham/Android/Sdk/ndk-bundle/toolchains/llvm/prebuilt/linux-x86_64/bin/aarch64-linux-android-strip" "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/liblbp.so")
    endif()
  endif()
endif()

if("x${CMAKE_INSTALL_COMPONENT}x" STREQUAL "xdevx" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/include/package_lbp" TYPE FILE FILES
    "/home/quyenpham/Downloads/ImageProcessing/app/src/main/cpp/lbplibrary/package_lbp/LBP.h"
    "/home/quyenpham/Downloads/ImageProcessing/app/src/main/cpp/lbplibrary/package_lbp/bglbp/BGLBP.h"
    "/home/quyenpham/Downloads/ImageProcessing/app/src/main/cpp/lbplibrary/package_lbp/cslbp/CSLBP.h"
    "/home/quyenpham/Downloads/ImageProcessing/app/src/main/cpp/lbplibrary/package_lbp/csldp/CSLDP.h"
    "/home/quyenpham/Downloads/ImageProcessing/app/src/main/cpp/lbplibrary/package_lbp/cssiltp/CSSILTP.h"
    "/home/quyenpham/Downloads/ImageProcessing/app/src/main/cpp/lbplibrary/package_lbp/elbp/ELBP.h"
    "/home/quyenpham/Downloads/ImageProcessing/app/src/main/cpp/lbplibrary/package_lbp/oclbp/OCLBP.h"
    "/home/quyenpham/Downloads/ImageProcessing/app/src/main/cpp/lbplibrary/package_lbp/olbp/OLBP.h"
    "/home/quyenpham/Downloads/ImageProcessing/app/src/main/cpp/lbplibrary/package_lbp/scslbp/SCSLBP.h"
    "/home/quyenpham/Downloads/ImageProcessing/app/src/main/cpp/lbplibrary/package_lbp/siltp/SILTP.h"
    "/home/quyenpham/Downloads/ImageProcessing/app/src/main/cpp/lbplibrary/package_lbp/varlbp/VARLBP.h"
    "/home/quyenpham/Downloads/ImageProcessing/app/src/main/cpp/lbplibrary/package_lbp/xcslbp/XCSLBP.h"
    )
endif()

if("x${CMAKE_INSTALL_COMPONENT}x" STREQUAL "xappx" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/bin" TYPE EXECUTABLE FILES "/home/quyenpham/Downloads/ImageProcessing/app/.externalNativeBuild/cmake/debug/arm64-v8a/lbplibrary/lbp")
  if(EXISTS "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/bin/lbp" AND
     NOT IS_SYMLINK "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/bin/lbp")
    if(CMAKE_INSTALL_DO_STRIP)
      execute_process(COMMAND "/home/quyenpham/Android/Sdk/ndk-bundle/toolchains/llvm/prebuilt/linux-x86_64/bin/aarch64-linux-android-strip" "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/bin/lbp")
    endif()
  endif()
endif()

