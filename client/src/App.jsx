import { useState } from "react"
import axios from "axios";

const App = () => {
  const [city,setCity]=useState('');
  const [weatherData, setWeatherData] = useState(null);

  const handleWeather = async (e) => {
    e.preventDefault();
    try {
      const {data} = await axios.get(`https://weather-web-backend.onrender.com/api/weather/${city}`);
      setWeatherData(data);  
    } catch (error) {
      console.error("Error fetching weather data:", error);
      alert("Failed to fetch weather data. Please try again.");
    }
  }

  return (
    <>
    <div className="w-full min-h-screen bg-image">
      <div className="flex flex-col items-center justify-center pt-24">
        <h1 className="text-white text-4xl font-bold">Weather App</h1>
        <p className="text-white">Get the latest weather updates</p>
        <div className="pt-10 w-full max-w-lg">
          <form className="flex w-full" onSubmit={handleWeather}>
            <input type="text" 
            placeholder="Enter city name" 
            className="p-2 rounded outline-none border-2
             border-blue-500 text-white w-[70%]" 
             value={city}
             onChange={(e) => setCity(e.target.value)}
             />
            <button type="submit" 
            className="bg-blue-500 text-white p-2 rounded"
            >Get Weather</button>
          </form>
        </div>
        <div className="">
          {weatherData && (
            <div className="bg-white p-4 rounded shadow-md">
              <h2 className="text-xl font-bold mb-2">{weatherData.city}</h2>
              <p className="text-gray-700">Temperature: {weatherData.temperature}°C</p>
              <p className="text-gray-700">Condition: {weatherData.condition}</p>
            </div>
          )}
        </div>
      </div>
    </div>
    </>
  )
}

export default App
