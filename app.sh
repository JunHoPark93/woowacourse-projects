echo "> 프로젝트 Clean 후 Build 시작"

./gradlew clean build

echo "> 현재 구동중인 앱 pid 확인"

CURRENT_PID=$(lsof -i:8080 | awk '{print $2}')

echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -2 $CURRENT_PID"
    kill -9 $CURRENT_PID
    sleep 5
fi

echo "> 새 어플리케이션 배포"

cd build/libs

nohup java -jar myblog-0.0.1-SNAPSHOT.jar &