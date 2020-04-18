# Community Home Assistant Add-ons: Rclone (unofficial)

![Supports aarch64 Architecture][aarch64-shield] ![Supports amd64 Architecture][amd64-shield] ![Supports armhf Architecture][armhf-shield] ![Supports armv7 Architecture][armv7-shield] ![Supports i386 Architecture][i386-shield]

Rclone bundled as an Home Assistant add-on.

## About

Fork of `https://github.com/alex3305/hassio-addons`

## Installation

Follow these steps to get the add-on installed on your system:

1. Navigate in your Home Assistant frontend to __Supervisor -> Add-on Store__
2. Add this new repository by URL (`https://github.com/fluffymonster/hassio-addons`)
3. Find the "Rclone" add-on and click it.
4. Click on the "INSTALL" button

## How to use

After installation you will need to generate a rclone configuration file. This can be done in several ways:

### Generate rclone config via SSH console (for RPi)

1. Open terminal on HASS.IO
2. run `wget -O /tmp/rclone.zip https://downloads.rclone.org/rclone-current-linux-arm.zip`
3. run `unzip /tmp/rclone.zip -d /tmp`
4. change to created directory `cd rclone-[version]-linux-arm`
5. Run `./rclone --config /data/rclone.conf config`
6. Set up your remote
7. Remove files from tmp folder `rm -R /tmp/rclone*`

### Generate rclone config locally

1. Download the [latest release](https://rclone.org/downloads/) for your platform and extract the rclone binary
2. Run `rclone config`
3. Set up your remote
4. Copy the generated Rclone config to your Hass.io host

### Generate rclone config through a Docker container

1. Run `docker run --rm -it --entrypoint /bin/sh rclone/rclone`
2. Run `rclone --config /data/rclone.conf config`
3. Set up your remote
4. Run `cat /data/rclone.conf` and copy over the contents to your Hass.io host

> __Note__ For more information regarding Rclone config, please read the [Rclone documentation](https://rclone.org/docs/).

### Example Rclone configuration

```conf
[myremote]
type = owncloud
url = https://some.owncloudhost.com/remote.php/webdav/
vendor = owncloud
user = hassio
pass = *** ENCRYPTED PASS ***
```

## Configuration

```yaml
configuration_path: /share/rclone/rclone.conf
remote: myremote
remote_path: /backups/
local_retention_days: 45
remote_retention_days: 15
```

### Option `configuration_path` (required)

Path to the Rclone configuration file. You can use the `/share/` or `/config/` directories for storing this file.

### Option `remote` (required)

Name of the remote to copy the Hass.io snapshots to.

### Option `remote_path` (required)

Path on the remote where the copied files should be stored. 

### Option `local_retention_days` (required)

The number of days the local files are kept. Files older than this date are pruned by this application. If for example the set value is 15, local files older than 15 days will be deleted.

> __Note__ This value should be higher than `remote_retention_days`.

### Option `remote_retention_days` (required)

The number of days the remote files are kept. Files older than this date are pruned by this application. If for example the set value is 15, remote files older than 15 days will be deleted.

## Automations

This add-on can easily be used with an automation. For instance:

The name of the addon i found by going to hass.io dashboard and then selecting the RClone addon and viewing the URL.

```yaml
- id: home_assistant_run_backup
  alias: Home Assistant backup
  trigger:
    platform: time
    at: '07:30'
  action:
    service: hassio.addon_start
    data_template:
      addon: xxxxxxxx_rclone
```

Which will prune local files and run Rclone copy at 07:30 in the morning.

## Known issues and limitations

* You will have to manually create rclone config
* Only a single remote is configurable

## Final notes

This project is not affiliated with Rclone, the Rclone Maintainers Team or Nick Craig-Wood, but simply a community effort. Rclone itself is distributed under the [MIT License](https://rclone.org/licence/).

[aarch64-shield]: https://img.shields.io/badge/aarch64-no-red.svg
[amd64-shield]: https://img.shields.io/badge/amd64-yes-green.svg
[armhf-shield]: https://img.shields.io/badge/armhf-yes-green.svg
[armv7-shield]: https://img.shields.io/badge/armv7-yes-green.svg
[i386-shield]: https://img.shields.io/badge/i386-yes-green.svg
[repository]: https://github.com/fluffymonster/hassio-addons