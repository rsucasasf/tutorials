-----------------------

**Table of Contents**

- Collectd
  - Install
  - Commands
  - Custom plugins (written in C)

-----------------------

#### INSTALL (1)

- Install (in **Ubuntu 16.04**):

```bash
sudo apt-get update
sudo apt-get install collectd collectd-utils
```

- *collectd.conf* location: `/etc/collectd/collectd.conf`

#### INSTALL (2)

- Install (in **Ubuntu 16.04**) from sources: https://github.com/collectd/collectd

```bash
git clone https://github.com/collectd/collectd.git
```

```bash
cd collectd
./build.sh
```

- If you get errors after executing build.sh, install requirements:

```bash
sudo apt-get install autoconf
sudo apt-get install libtool
sudo apt-get install bison
sudo apt-get install pkg-config
sudo apt-get install flex
```

- And execute build again:

```bash
./build.sh
```

- Configuring / Compiling / Installing:

```bash
(sudo ./configure && sudo make && sudo make install)
sudo ./configure
sudo make
sudo make install
```

-----------------------

#### COMMANDS

- Start Collectd:

```bash
sudo ./collectd
sudo /home/atos/collectd/collectd
```
- Stop Collectd:

```bash
sudo kill XXXX
```

- Plugins: Edit 'collectd.conf'

```bash
/opt/collectd/etc
vi collectd.conf
sudo vi /opt/collectd/etc/collectd.conf
```

-----------------------

#### Custom plugins (written in C)

- Install custom plugin written in C:

  1. Compile program using src from 'collectd' (git - download): <nvidia_test_plugin>

```bash
gcc -DHAVE_CONFIG_H -Wall -Werror -g -O2 -shared -fPIC -I/home/atos/collectd/src/ -I/home/atos/collectd/src/daemon/ -o nvidia_plugin.so nvidia_test_plugin.c
```

  2. Modify permissions and owner

```bash
sudo chmod 0755 nvidia_plugin.so
sudo chown -R root:root nvidia_plugin.so
```

  3. Copy file in collectd plugins library: /opt/collectd/lib/collectd

```bash
sudo cp nvidia_plugin.so /opt/collectd/lib/collectd/nvidia_plugin.so
```

  4. Edit 'collectd.conf' and enable CSV plugin

```bash
sudo vi /opt/collectd/etc/collectd.conf
```

  5. Start collectd and check errors

```bash
sudo /home/atos/collectd/collectd
```

  6. Stop all:

```bash
sudo netstat -tulpn
...  
sudo kill xxxx
```
