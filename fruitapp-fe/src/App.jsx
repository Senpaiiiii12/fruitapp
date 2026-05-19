import { useState } from 'react'
import axios from 'axios'

function App() {

  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')

  const [loggedIn, setLoggedIn] = useState(false)

  const [fruits, setFruits] = useState([])

  const [name, setName] = useState('')
  const [quantity, setQuantity] = useState('')
  const [price, setPrice] = useState('')

  const [editingId, setEditingId] = useState(null)

  const authConfig = {
    auth: {
      username,
      password
    }
  }

  const loadFruits = () => {

    axios.get(
      'http://localhost:8080/api/fruits',
      authConfig
    )

    .then(res => {
      setFruits(res.data)
    })

    .catch(err => {
      console.log(err)
    })
  }

  const login = () => {

    axios.get(
      'http://localhost:8080/api/fruits',
      authConfig
    )

    .then(res => {

      setLoggedIn(true)

      setFruits(res.data)

    })

    .catch(err => {

      console.log(err)

      alert("Login failed")

    })
  }

  const logout = () => {

    setLoggedIn(false)

    setFruits([])

    setUsername('')
    setPassword('')

  }

  const addFruit = () => {

    const fruit = {
      name,
      quantity,
      price
    }

    axios.post(
      'http://localhost:8080/api/fruits',
      fruit,
      authConfig
    )

    .then(() => {

      loadFruits()

      clearForm()

    })

    .catch(err => {

      console.log(err)

      alert("Only ADMIN can add")

    })
  }

  const deleteFruit = (id) => {

    axios.delete(
      `http://localhost:8080/api/fruits/${id}`,
      authConfig
    )

    .then(() => {

      loadFruits()

    })

    .catch(err => {

      console.log(err)

      alert("Only ADMIN can delete")

    })
  }

  const editFruit = (fruit) => {

    setEditingId(fruit.id)

    setName(fruit.name)

    setQuantity(fruit.quantity)

    setPrice(fruit.price)

  }

  const updateFruit = () => {

    const fruit = {
      name,
      quantity,
      price
    }

    axios.put(
      `http://localhost:8080/api/fruits/${editingId}`,
      fruit,
      authConfig
    )

    .then(() => {

      loadFruits()

      clearForm()

    })

    .catch(err => {

      console.log(err)

      alert("Only ADMIN can update")

    })
  }

  const clearForm = () => {

    setName('')
    setQuantity('')
    setPrice('')

    setEditingId(null)

  }

  const isAdmin = username === 'admin'

  return (

    <div style={{ padding: '20px' }}>

      <h1>Fruit Manager</h1>

      {
        !loggedIn && (

          <div>

            <input
              placeholder="Username"
              onChange={(e) => setUsername(e.target.value)}
            />

            <br /><br />

            <input
              type="password"
              placeholder="Password"
              onChange={(e) => setPassword(e.target.value)}
            />

            <br /><br />

            <button onClick={login}>
              Login
            </button>

          </div>

        )
      }

      {
        loggedIn && (

          <div>

            <h3>
              Xin chào {username}
            </h3>

            <button onClick={logout}>
              Logout
            </button>

            <hr />

            {
              isAdmin && (

                <div>

                  <h2>
                    {
                      editingId
                        ? 'Edit Fruit'
                        : 'Add Fruit'
                    }
                  </h2>

                  <input
                    placeholder="Name"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                  />

                  <br /><br />

                  <input
                    placeholder="Quantity"
                    value={quantity}
                    onChange={(e) => setQuantity(e.target.value)}
                  />

                  <br /><br />

                  <input
                    placeholder="Price"
                    value={price}
                    onChange={(e) => setPrice(e.target.value)}
                  />

                  <br /><br />

                  {
                    editingId ? (

                      <button onClick={updateFruit}>
                        Update
                      </button>

                    ) : (

                      <button onClick={addFruit}>
                        Add
                      </button>

                    )
                  }

                  <button onClick={clearForm}>
                    Clear
                  </button>

                  <hr />

                </div>

              )
            }

            <table border="1" cellPadding="10">

              <thead>

                <tr>

                  <th>ID</th>
                  <th>Name</th>
                  <th>Quantity</th>
                  <th>Price</th>

                  {
                    isAdmin && (
                      <th>Action</th>
                    )
                  }

                </tr>

              </thead>

              <tbody>

                {
                  fruits.map(fruit => (

                    <tr key={fruit.id}>

                      <td>{fruit.id}</td>

                      <td>{fruit.name}</td>

                      <td>{fruit.quantity}</td>

                      <td>{fruit.price}</td>

                      {
                        isAdmin && (

                          <td>

                            <button
                              onClick={() => editFruit(fruit)}
                            >
                              Edit
                            </button>

                            <button
                              onClick={() =>
                                deleteFruit(fruit.id)
                              }
                            >
                              Delete
                            </button>

                          </td>

                        )
                      }

                    </tr>

                  ))
                }

              </tbody>

            </table>

          </div>

        )
      }

    </div>
  )
}

export default App