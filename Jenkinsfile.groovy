node{
    stage("Pull Repo"){
        git 'git@github.com:addiani/cool_website.git'
       
    }
    stage("webserver Install"){
        sh "ssh ec2-user@18.188.162.93       sudo yum install httpd -y"
    }
}
