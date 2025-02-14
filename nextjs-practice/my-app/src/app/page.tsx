import "./globals.css";

export default function Home() {
    return (
        <>
            <h2>Welcome</h2>
            Hello, Web!
            <br/>
            <img src="/image/noum.avif" alt="" width="15%"></img>
        </>
    );
}

// img 태그를 사용할 때, src 속성에 이미지 경로를 넣어주면, 이미지가 렌더링된다.
// 이미지 경로는 public 폴더를 기준으로 작성한다.
// public 폴더는 next.js 프로젝트의 루트에 위치한다.

