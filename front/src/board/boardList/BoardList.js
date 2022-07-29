import React, { useEffect, useState } from 'react'
import axios from 'axios'
import SelectOneFile from '../../commons/Files/SelectOneFile';
import List from '../boardList/boardListComponemt/List';

const BoardList = () => {
  const [board, setBoard] = useState([]); //변수 초기화

  useEffect(() => {
    axios({
      method: 'get',
      url: '/GareBnB/board/boardList.do'
      //서버에서 리스트 요청
    }).then(Response => {

      const url = Response.data.map(async list => {

        await SelectOneFile('0', list.BOARD_NO).then(Res => {
          //요청된 리스트의 게시글 넘버로 메인 이미지 요청

          list['URL'] = "data:image/;base64," + Res.URL
        })
          //변수에 URL 요소를 추가하고 서버로부터 리턴 받은 이미지를 문자화해서 저장
        return list
      })

      Promise.all(url).then((data) => { setBoard(data) });
      //async - await 로 받아온 객체는 promise 객체이므로 이를 변환해서 저장 
    })

  }, [])


  return (
    <div>
      {console.log(board)}
      {List(board)}
    </div>
  )
}

export default BoardList