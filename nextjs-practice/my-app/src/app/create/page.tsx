"use client";

import {useRouter} from "next/navigation";

interface CreateProps {
    title: string;
    description: string;
}


export default function Create() {
    const router = useRouter(); // navigation hook을 사용합니다.
    return (
        <div>
            <form onSubmit={(event) => {
                // 페이지가 리로드 되는 것을 막기 위해 preventDefault()를 호출합니다.
                event.preventDefault();
                // event 타입 지정이 필요합니다.
                // 이벤트가 발생한 form 요소를 event.target을 통해 참조할 수 있습니다.
                // currentTarget은 이벤트가 발생한 요소를 참조합니다.
                const formData = new FormData(event.currentTarget);
                const title = formData.get("title") as string; // name="title" 값을 가져옴
                const description = formData.get("description") as string;
                // form 요소의 name 속성을 통해 참조할 수 있습니다.
                console.log(title);
                console.log(description);

                const options = {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({title, description}),
                }


                fetch(`${process.env.NEXT_PUBLIC_API_URL}/topics`, options)
                    .then((response) => response.json())
                    .then((result) => {
                        console.log(result)
                        router.push(`/read/${result.id}`); // 페이지 이동
                        router.refresh(); // 페이지 새로고침
                    });
            }}>
                <p>
                    <input type="text" name="title" placeholder="제목"/>
                </p>
                <p>
                    <textarea name="description" placeholder="내용"/>
                </p>
                <p>
                    <input type="submit" value="create"/>
                </p>
            </form>
        </div>
    )
}
