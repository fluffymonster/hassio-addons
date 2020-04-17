ARG BUILD_FROM=hassioaddons/base:7.1.0
# hadolint ignore=DL3006
FROM ${BUILD_FROM}

# Build arguments
ARG BUILD_ARCH
ARG BUILD_DATE
ARG BUILD_REF
ARG BUILD_VERSION

COPY run.sh /

# Setup base
RUN wget --quiet -O /tmp/rclone.zip https://downloads.rclone.org/rclone-current-linux-arm.zip

# Labels

