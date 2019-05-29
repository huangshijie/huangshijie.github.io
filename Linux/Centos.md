# Centos Environment

## JDK install

- Get newest download command from [download-java8.sh](https://gist.github.com/hgomez/9650687)

- insert jdk path to environment
  
  ```sh
  export JAVA_HOME=/root/jdk
  export JRE_HOME=${JAVA_HOME}/jre
  export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
  export PATH=${JAVA_HOME}/bin:$PATH
  ```

- make this file effective

  ```sh
  source /etc/profile
  ```

## Maven install

- Get maven binaries file from offical web.
  
  ```sh
  wget http://mirror.bit.edu.cn/apache/maven/maven-3/3.6.1/binaries/apache-maven-3.6.1-bin.tar.gz
  ```

  ```sh
  tar xzvf apache-maven-3.6.1-bin.tar.gz
  ```

  ```sh
  export MVN_HOME=/root/maven
  export PATH=${MVN_HOME}/bin:$PATH
  ```

- make this file effective

  ```sh
  source /etc/profile
  ```