ARG BUILD_FROM
FROM $BUILD_FROM

ENV LANG C.UTF-8
ENV WX_USER=weewx

ARG WEEWX_VERSION=4.5.1
ARG WEEWX_SHA=9650f9a4ce0f300a652d926820bc5b683a1826fd668c0e71413e88c772d7f056
ARG WEEGREEN_VERSION=v0.12
ARG WX_GROUP=dialout
ARG WX_UID=2071
ARG XTIDE_SHA=e5c4afbb17269fdde296e853f2cb84845ed1c1bb1932f780047ad71d623bc681

# Copy data for add-on
COPY setup/ /tmp/setup/


RUN apk add --no-cache --update \
      curl freetype libjpeg libstdc++ openssh openssl python3 py3-cheetah \
      py3-configobj py3-mysqlclient py3-pillow py3-requests py3-six py3-usb \
      rsync rsyslog tzdata nano sshpass && \
      adduser -u $WX_UID -s /bin/sh -G $WX_GROUP -D $WX_USER && \
      mkdir /tmp/build && cd /tmp/build && \
      curl -sLo weewx.tar.gz \
      http://www.weewx.com/downloads/released_versions/weewx-$WEEWX_VERSION.tar.gz && \
      echo "$WEEWX_SHA  weewx.tar.gz" >> /tmp/build/checksums && \
      sha256sum -c /tmp/build/checksums && \
      apk add --no-cache --virtual .fetch-deps \
      file freetype-dev g++ gawk gcc git jpeg-dev libpng-dev make musl-dev \
      py3-pip py3-wheel python3-dev zlib-dev && \
      pip install -r /tmp/setup/requirements.txt && \
      ln -s python3 /usr/bin/python && \
      tar xf weewx.tar.gz --strip-components=1 && \
      cd /tmp/build && \
      ./setup.py build && ./setup.py install < /tmp/setup/install-input.txt && \
	  mkdir /tmp/extension && cd /tmp/extension && \
	  wget -O weewx-mqtt.zip https://github.com/matthewwall/weewx-mqtt/archive/master.zip && \
	  wget -O weewx-interceptor.zip https://github.com/matthewwall/weewx-interceptor/archive/master.zip && \
	  pip3 install paho-mqtt && cd /home/weewx && \
	  bin/wee_extension --install /tmp/extension/weewx-mqtt.zip && \
	  bin/wee_extension --install /tmp/extension/weewx-interceptor.zip 

COPY rootfs /

#CMD [ "/tmp/setup/run.sh" ]

# Labels
LABEL \
    io.hass.arch="armhf|aarch64" \
    io.hass.type="addon" \
    io.hass.version=${BUILD_VERSION}