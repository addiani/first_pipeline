node{
   properties([parameters([string(defaultValue: 'IP', description: 'where should I build?', name: 'Env', trim: false)]), pipelineTriggers([pollSCM('* * * * *')])])
   
   
   
    stage("Pull Repo"){
        git 'git@github.com:addiani/cool_website.git'
       
    }
    stage("webserver Install"){
        sh "ssh ec2-user@${Env}      sudo yum install httpd -y"
    }
    stage("Index file"){
        sh "scp index.html                    ec2-user@${Env}:/tmp"

    }
    stage("move Index"){
        sh "ssh ec2-user@${Env}    sudo mv /tmp/index.html /var/www/html/index.html"
    }
    stage("restart httpd"){
        sh "ssh ec2-user@${Env}      sudo systemctl restart httpd"
    }
        
}

