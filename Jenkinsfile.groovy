node{
    properties([pipelineTriggers([pollSCM('* * * * *')])])
    stage("Pull Repo"){
        git 'git@github.com:addiani/cool_website.git'
       
    }
    stage("webserver Install"){
        sh "ssh ec2-user@18.188.162.93       sudo yum install httpd -y"
    }
    stage("Index file"){
        sh "scp index.html                    ec2-user@18.188.162.93:/tmp"

    }
    stage("move Index"){
        sh "ssh ec2-user@18.188.162.93     sudo mv /tmp/index.html /var/www/html/index.html"
    }
    stage("restart httpd"){
        sh "ssh ec2-user@18.188.162.93      sudo systemctl restart httpd"
    }
        
}

