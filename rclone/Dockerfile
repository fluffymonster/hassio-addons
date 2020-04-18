ARG BUILD_FROM
FROM $BUILD_FROM

ENV LANG C.UTF-8

# Set shell
SHELL ["/bin/bash", "-o", "pipefail", "-c"]

# Copy data for add-on
COPY run.sh /

# Build arguments
ARG BUILD_DATE
ARG BUILD_REF
ARG BUILD_VERSION

# Setup base system
ARG BUILD_ARCH=armv7
RUN apk add --no-cache unzip findutils && \
    \
    if [ "${BUILD_ARCH}" = "amd64" ]; then ARCH="amd64"; fi && \
    if [ "${BUILD_ARCH}" = "armv7" ]; then ARCH="arm"; fi && \
    if [ "${BUILD_ARCH}" = "armhf" ]; then ARCH="arm"; fi && \
    if [ "${BUILD_ARCH}" = "i386" ]; then ARCH="386"; fi && \
    \
    wget --quiet -O /tmp/rclone.zip https://downloads.rclone.org/v${BUILD_VERSION}/rclone-v${BUILD_VERSION}-linux-${ARCH}.zip  && \
    unzip /tmp/rclone.zip -d /tmp && \
    cp /tmp/rclone-v${BUILD_VERSION}-linux-${ARCH}/rclone /usr/bin/rclone && \
    chmod a+x /usr/bin/rclone && \
    chmod a+x /run.sh && \
    rm -rf /tmp/*
	
CMD [ "/run.sh" ]

# Labels
LABEL \
    io.hass.name="RClone" \
    io.hass.description="RClone Description (io.hass)" \
    io.hass.arch="${BUILD_ARCH}" \
    io.hass.type="addon" \
    io.hass.version=${BUILD_VERSION} \
    maintainer="Fluffy Monster <fluffy.monster@gmail.com>" \
    org.label-schema.description="RClone Description." \
    org.label-schema.build-date=${BUILD_DATE} \
    org.label-schema.name="RClone" \
    org.label-schema.schema-version="1.0" \
    org.label-schema.url="https://community.home-assistant.io/t/home-assistant-community-add-on-plex-media-server/54383?u=frenck" \
    org.label-schema.usage="https://github.com/fluffymonster/hassio-addons/rclone/tree/master/README.md" \
    org.label-schema.vcs-ref=${BUILD_REF} \
    org.label-schema.vcs-url="https://github.com/fluffymonster/hassio-addons/rclone" \
    org.label-schema.vendor="Home Assistant Community Add-ons"