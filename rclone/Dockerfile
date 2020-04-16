ARG BUILD_FROM

FROM $BUILD_FROM
ENV LANG C.UTF-8

# Build arguments
ARG BUILD_ARCH
ARG BUILD_DATE
ARG BUILD_REF
ARG BUILD_VERSION

# Copy root filesystem
COPY rootfs /

RUN apk add --no-cache unzip findutils && \
    wget --quiet -O /tmp/rclone.zip https://downloads.rclone.org/rclone-current-linux-arm.zip


