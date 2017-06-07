-----------------------

**Table of Contents**

- Docker
  - Install
  - Commands

-----------------------

#### INSTALL

- For Windows (7 or earlier): Docker Toolbox

        https://www.docker.com/products/docker-toolbox

  It uses VirtualBox and an image with all Docker components

- Ubuntu 16.04:

  Install Docker components in Ubuntu 16: https://docs.docker.com/engine/installation/linux/ubuntulinux/#install-the-latest-version

    1. Check kernel version:
      > uname -r

    2. sudo apt-get update

    3. sudo apt-get install apt-transport-https ca-certificates

    4. sudo apt-key adv --keyserver hkp://ha.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D

    5. echo "deb https://apt.dockerproject.org/repo ubuntu-xenial main" | sudo tee /etc/apt/sources.list.d/docker.list

    6. sudo apt-get update

    7. Verify that APT is pulling from the right repository:
      > apt-cache policy docker-engine

       From now on when you run apt-get upgrade, APT pulls from the new repository.

    8. To install the linux-image-extra-* packages:
      > sudo apt-get update
      > sudo apt-get install linux-image-extra-$(uname -r) linux-image-extra-virtual

    9. Install Docker
      > sudo apt-get update
      > sudo apt-get install docker-engine
      > sudo service docker start
      > sudo docker run hello-world

-----------------------

#### COMMANDS
      	* sh to image
      		> sudo docker run --rm -it 291aa3a53362 sh
      	* Interactively Create a Docker Container
      		> sudo docker run -i -t rsucasas/compss-tango-cuda75 bash
      		> sudo docker run -i -t 8e33bc76bbfa bash
      	* Remove all stopped containers.
      		> sudo docker rm $(sudo docker ps -a -q)
      	* Remove all (untagged images)
      		> sudo docker rmi $(sudo docker images | grep "^<none>" | awk "{print $2}")
      		> sudo docker images -q | xargs sudo docker rmi
        * Remove "duplicated id" images
      		> sudo docker images | grep 078e9f235a3b | awk '{print $1 ":" $2}' | xargs sudo docker rmi

      	REMOVE ALL =====>
      				sudo docker rm $(sudo docker ps -a -q)
      				sudo docker images -q | xargs sudo docker rmi
      				sudo docker images | grep 9acd8d61de02 | awk '{print $1 ":" $2}' | xargs sudo docker rmi
      				...
      				sudo docker images -q | xargs sudo docker rmi

      	* Show images:
      		> sudo docker images
      			---------------------------------------------------------------------------------------
      			REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
      			docker-whale        latest              c00d01da7da5        2 minutes ago       284 MB
      			hello-world         latest              48b5124b2768        9 days ago          1.84 kB
      			docker/whalesay     latest              6b362a9f73eb        20 months ago       247 MB
      			---------------------------------------------------------------------------------------
      	* Show (running) containers:
      		> sudo docker ps
      	* Run docker ps -a to show all containers on the system:
      		> sudo docker ps -a
      			--------------------------------------------------------------------------------------------------------------------------------------------
      			CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS                     PORTS    NAMES
      			57d43ac03565        docker-whale        "/bin/sh -c '/usr/..."   7 minutes ago       Exited (0) 7 minutes ago            xenodochial_mcnulty
      			7eeefa6d3f17        docker-whale        "/bin/sh -c '/usr/..."   7 minutes ago       Exited (0) 7 minutes ago            musing_spence
      			706866f404fb        hello-world         "/hello"                 3 days ago          Exited (0) 3 days ago               amazing_mahavira
      			--------------------------------------------------------------------------------------------------------------------------------------------
      	* build the image using the docker build command. The -t parameter gives your image a tag, so you can run it more easily later.
      	  Don’t forget the . command, which tells the docker build command to look in the current directory for a file called Dockerfile.
      		> sudo docker build -t docker-whale .
      	  The "docker build -t docker-whale ." command reads the Dockerfile in the current directory and processes its instructions one by
      	  one to build an image called docker-whale on your local machine.
      	* Run image
      		> sudo docker run docker-whale
      	* View stats (for exampl, memory used by docker containers)
      		> sudo docker stats -a
      			-----------------------------------------------------------------------------------------------------------------------------
      			CONTAINER           CPU %               MEM USAGE / LIMIT   MEM %               NET I/O             BLOCK I/O           PIDS
      			57d43ac03565        --                  -- / --             --                  --                  --                  --
      			7eeefa6d3f17        --                  -- / --             --                  --                  --                  --
      			706866f404fb        --                  -- / --             --                  --                  --                  --
      			-----------------------------------------------------------------------------------------------------------------------------
