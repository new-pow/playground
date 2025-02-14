// server components
export default async function Read(props: any) {
    const resp = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/topics/${props.params.id}`);
    const topic = await resp.json();
    return (
        <>
            <h2>{topic.title}</h2>
            <p>{topic.description}</p>
        </>
    )
}
// params 에는 라우팅 경로에 있는 변수들이 들어있다.
// 예를 들어, /read/1 이라는 경로로 접근했다면, params.id 는 1이 된다.
