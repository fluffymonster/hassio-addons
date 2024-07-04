ARG BUILD_FROM

FROM $BUILD_FROM

ENV LANG C.UTF-8
ENV WX_USER=weewx

ARG WEEWX_VERSION=4.5.1

ARG WEEGREEN_VERSION=v0.12
ARG WX_GROUP=dialout
ARG WEEWX_UID=2071

ARG ARCHIVE="weewx-${WEEWX_VERSION}.tar.gz"

WORKDIR /tmp

# Copy data for add-on
COPY setup/ /tmp/setup/

# Build environment
RUN apt-get update

RUN apt-get install -y sqlite3 wget sudo openssh-client sshpass rsync \
         python-is-python3 python3-pip python3-configobj python3-cheetah python3-pil python3-usb python3-setuptools \
         supervisor tzdata jq nano

RUN pip3 install pyserial

RUN addgroup --system --gid ${WEEWX_UID} weewx \
  && adduser --system --uid ${WEEWX_UID} --ingroup weewx weewx

# Download sources and verify hashes
RUN wget -O "${ARCHIVE}" "https://weewx.com/downloads/released_versions/${ARCHIVE}"
RUN wget -O weewx-mqtt.zip https://github.com/matthewwall/weewx-mqtt/archive/master.zip
RUN wget -O weewx-interceptor.zip https://github.com/matthewwall/weewx-interceptor/archive/master.zip
RUN sha256sum -c < ./setup/hashes

# WeeWX setup
RUN mkdir /tmp/build && \
      tar --extract --gunzip --directory /tmp/build --strip-components=1 --file "${ARCHIVE}" && \
      cd /tmp/build && \
      ./setup.py build && \
      ./setup.py install < /tmp/setup/install-input.txt

# WeeWX Extensions
RUN pip3 install paho-mqtt && cd /home/weewx && \
      bin/wee_extension --install /tmp/weewx-mqtt.zip && \
      bin/wee_extension --install /tmp/weewx-interceptor.zip 


COPY rootfs /

# Labels
LABEL \
    io.hass.arch="armhf|aarch64|i386" \
    io.hass.type="addon" \
    io.hass.version=${BUILD_VERSION}
