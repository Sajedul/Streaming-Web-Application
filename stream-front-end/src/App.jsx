import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import VideoUpload from './components/VideoUpload'
import { Toaster } from "react-hot-toast";

function App() {
  const [count, setCount] = useState(0);
  const [videoId, setVideoId] = useState("96cb4689-202c-494c-81d0-f9c8a91e06fa");

  return (
    <>
      <Toaster />
      <div className="flex flex-col items-center space-y-5 justify-center py-9">
        <h1 className="text-5xl font-extrabold text-blue-900 dark:text-gray-100" > Welcome to video Streaming app</h1>

        {/* <div>
          <h1 className="text-white">Playing video</h1>
          <video style={{width: 500,height:300}}
          src={`http://localhost:9595/api/v1/videos/stream/range/${videoId}`} controls></video>
        </div> */}

        <div>
          <video
            id="my-video"
            class="video-js"
            controls
            preload="auto"
            width="640"
            height="264"
           
            data-setup="{}"
          >
            
            <source src={`http://localhost:9595/api/v1/videos/stream/range/${videoId}`} type="video/webm" />
            <p class="vjs-no-js">
              To view this video please enable JavaScript, and consider upgrading to a
              web browser that
              <a href="https://videojs.com/html5-video-support/" target="_blank"
              >supports HTML5 video</a
              >
            </p>
          </video>
        </div>

        <VideoUpload />
      </div>

    </>
  );
}

export default App
