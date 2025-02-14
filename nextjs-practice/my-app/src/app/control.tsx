"use client";
import Link from "next/link";
import {useParams, useRouter} from "next/navigation";

export function Control() {
    const params = useParams(); // navigation hook을 사용합니다.
    const id = params.id;
    const router = useRouter(); // navigation hook을 사용합니다.
    return (
        <ul>
            <li><Link href="/create">Create</Link></li>
            {
                id ?
                    <>
                        <li><Link href={"/update/" + id}>Update</Link></li>
                        <li><input type="button" value="delete" onClick={() => {
                            const options = {method: 'DELETE'}
                            fetch(`${process.env.NEXT_PUBLIC_API_URL}/topics/${id}`, options)
                                .then((response) => response.json())
                                .then(() => {
                                    router.push(`/`); // 페이지 이동
                                    router.refresh(); // 페이지 새로고침
                                });
                        }
                        }/></li>
                    </>
                    : null
            }
        </ul>
    );
}
