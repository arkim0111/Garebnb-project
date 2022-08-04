import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './sidestyle.css'
import {AiFillAlert, AiFillDatabase, AiFillCarryOut, AiFillEye, AiFillNotification, AiFillInteraction, AiOutlineDown} from "react-icons/ai";
import { Link, Navigate, useNavigate } from 'react-router-dom';
import '../../node_modules/bootstrap/dist/js/bootstrap'
import HostSidebar from './HostSidebar';
import Auth from '../login/Auth';

// react-icons names -> fa로 통일



const Sidebar = () => {

  const mem_id = localStorage.getItem("MEM_ID");
  const [memberLevel, setMemberLevel] = useState({
    MEM_LEVEL:''
  });
  console.log(mem_id);
  
 
  //level 2 -> 호스트,,,,
  useEffect(() => { // 레벨 4 이하인(일반,호스트,관리자) 접근 가능. MEM_IDX 받아오기
    Auth(4).then(Res => {
          setMemberLevel({
            ...memberLevel,
            'MEM_LEVEL': Res.MEM_LEVEL,
            })
          })
  }, [])
  
  console.log(memberLevel);  

  

  return (
    <div className='col'>

      
        <div className='navigation'>
          <ul className='big_menu1'>
          <li>
              <Link to='/myPage/ReserveListPage'>
                <span className='icon'><AiFillDatabase className='fa'/></span>
                <span className='title'>RESERVATION</span>
              </Link>
            </li>

              <li>
              <Link to="/myPage/memUseListPage">
                <span className='icon'><AiFillCarryOut className='fa' /></span>
                <span className='title'>이용내역</span>
              </Link>
              </li>

              <li>
              <Link to="/myPage/qna">
                <span className='icon'><AiFillNotification/></span>
                <span className='title'> QNA </span>
              </Link>
              </li>

              <li>
              <Link to="/myPage/report">
                <span className='icon'><AiFillAlert className='fa'/></span>
                <span className='title'>REPORT</span>
              </Link>
              </li>

              <li>
              <Link to="/myPage/member/MemDetail">
                <span className='icon'><AiFillEye/></span>
                <span className='title'>회원정보보기</span>
              </Link>
              </li>

              <li>
              <Link to="/myPage/member/MemChange">
                <span className='icon'><AiFillInteraction/></span>
                <span className='title'>TOHOST</span>
              </Link>
          </li>

          <li>
              <Link to="/admin">
                <span className='icon'><AiFillInteraction/></span>
                <span className='title'>admin 메뉴들</span>
              </Link>
          </li>
          </ul>

          {/* ----------------------------------------------------------------------------------------- */}
           {/* {memberLevel === 1 && } */}

           <HostSidebar/>
          
          
          
        </div>

        {/* <div class="toggle"><AiOutlineMenu className='fa' onClick={toggleMenu()}/></div> */}

        
              
</div>

  )
}

export default Sidebar;