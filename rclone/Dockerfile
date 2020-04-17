ARG BUILD_FROM

FROM $BUILD_FROM
ENV LANG C.UTF-8

ARG BUILD_VERSION

COPY run.sh /

# Setup base
RUN wget --quiet -O /tmp/rclone.zip https://downloads.rclone.org/rclone-current-linux-arm.zip

# Labels

