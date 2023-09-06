import {useState,useEffect} from "react";
import axios from 'axios';

export default function FetchUserData() {
    const [userData, setUserData] = useState([]);

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const response = await axios.get('/users');
                setUserData(response.data);
            } catch (error) {
                console.error('Error fetching user data:', error);
            }
        };

        fetchUserData();
    }, []);

    return (
        <div>
            <h2>User Data</h2>
            <ul>
                {userData.map((user) => (
                    <li key={user.id}>
                        {user.name} {user.userSurname}
                    </li>
                ))}
            </ul>
        </div>
    );
}
