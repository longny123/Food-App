import {getListUserData, getUserData, userLogin} from '../service/DataService'
import axios from 'axios';

jest.mock('axios')

test('Calling get data user', () =>{
    expect(getUserData()).not.toBeNull()
})
test('Calling get list data user', () =>{
    expect(getListUserData()).not.toBeNull()
})
test('Calling login data user', () =>{
    const user = [{
       userId: "wFSMXF4L6j5XYDJikq2h9ZYaPdLyaV"
    }];
    const resp = {data: user};
    axios.post.mockResolvedValue(resp)
    return userLogin(resp.data,resp.data).then(data => expect(data).toEqual(user))
})
test('Calling get data user', () =>{
    expect(getListUserData()).not.toBeNull()
})
test('Calling get data user', () =>{
    expect(getListUserData()).not.toBeNull()
})