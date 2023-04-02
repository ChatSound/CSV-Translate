# CSV-Translate

### 프로젝트 클론

- 터미널에서 `git clone https://github.com/ChatSound/CSV-Translate.git`

### 라이브러리 추가

1. `json.jar`파일을 우클릭한다.
2. `Add as Library` 클릭

###  환경변수(본인의 CLIENT_ID, CLIENT_SECRET 추가)

```java
private static final String CLIENT_ID = System.getenv("CLIENT_ID");
private static final String CLIENT_SECRET = System.getenv("CLIENT_SECRET");
```

<div>
<img src="https://user-images.githubusercontent.com/97272787/229340487-4a5ec9ee-21fc-468f-8220-814e61430fa7.png">
</div>

1. 우측 상단 `Current File` - `Edit Configurations...` 순서대로 클릭
2. `Environment variables` 우측의 문서 모양 클릭 
3. `Environment variables` 창에서 `+`를 클릭하고 본인의 Papago API ID와 SECRET을 입력하면 된다.


## 실행결과

- 약 205 ~ 215개의 행이 생성됨.

```csv
audiocap_id,캡션
91139,물이 쏟아지자 여자가 근처에서 말한다
58146,여러 개의 쨍그랑 소리와 쨍그랑 소리
11542,"바람이 불고, 벌레가 지저귀고, 바스락거리는 소리가 난다"
11543,바람이 불고 바스락거리는 소리가 난다
11540,사람이 휘파람을 불고 있다
11541,"모터보트가 순항하고, 한 남자가 말한다."
11546,접시가 달그락거리는 동안 음식의 지글거리는 소리
11547,타자를 치는 동안 누군가가 딸꾹질을 한다
11544,남자와 여자가 말할 때 항공기 엔진이 윙윙거린다
11545,쉭쉭거리는 소리와 함께 접시와 연설이 이어진다
89378,여자는 중간에 무언가가 몇 번 뿌려지는 동안 내내 이야기를 하고 있다
89379,수도꼭지에서 물이 쏟아지자 한 남자가 근처에서 이야기를 한다
11548,파리가 윙윙거리는 소리보다 덜컹거리는 소리가 먼저 난다
11549,개는 조용히 낑낑거린다
5988,엔진이 크게 회전함
5989,다 큰 암컷은 말하고 고양이는 울부짖는다
19399,어린이가 연설을 하고 군중들이 박수를 친다
19398,개는 두 번 짖고 나서 낑낑거린다
5982,자동차가 엔진을 돌릴 때 쉭쉭거리는 소리가 크게 들린다
5983,물 흐르는 소리에 뒤에서 비명을 지르는 아이들
```

## 주의 사항

- 기존 csv 에서 현재 작성한 행까지 이어서 만들게 되어 있기 때문에 반드시 팀원에게 물어보고, 프로젝트를 pull 한다.
- 작성이 다 끝나면 `commit` & `push`를 진행한 후 팀원에게 반드시 알린다.(slack 등 협업 프로그램에 알림 연동안해놓음)