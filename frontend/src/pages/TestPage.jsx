import {useNavigate} from 'react-router-dom';
export default function  TestPage(){
    const navigate=useNavigate();
  const getQuestionPage=()=>{
        navigate("/questions")
    }
    return(<div>

    </div>)
}