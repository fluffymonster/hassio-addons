ARG BUILD_FROM=hassioaddons/base:7.1.0
# hadolint ignore=DL3006
FROM ${BUILD_FROM}

# Build arguments
ARG BUILD_ARCH
ARG BUILD_DATE
ARG BUILD_REF
ARG BUILD_VERSION

# Copy root filesystem
COPY run.sh /

# Setup base
RUN wget --quiet -O /tmp/rclone.zip https://downloads.rclone.org/rclone-current-linux-arm.zip

# Labels
LABEL \
    io.hass.name="RClone" \
    io.hass.description="Rclone add-on by Fluffy Monster Hass.io Add-ons" \
    io.hass.arch="${BUILD_ARCH}" \
    io.hass.type="addon" \
    io.hass.version=${BUILD_VERSION} \
    maintainer="Fluffy Monster <fluffymonster@gmail.com>" \
    org.label-schema.description="Rclone add-on by Fluffy Monster Hass.io Add-ons" \
    org.label-schema.build-date=${BUILD_DATE} \
    org.label-schema.name="Example" \
    org.label-schema.schema-version="1.0" \
    org.label-schema.url="https://addons.community" \
    org.label-schema.usage="https://github.com/fluffymonster/hassio-addons/tree/master/README.md" \
    org.label-schema.vcs-ref=${BUILD_REF} \
    org.label-schema.vcs-url="https://github.com/fluffymonster/hassio-addons/rclone" \
    org.label-schema.vendor="Fluffy Monster Hass.io Addons"
