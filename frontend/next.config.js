/** @type {import('next').NextConfig} */
const nextConfig = {
  typescript: {
    ignoreBuildErrors: true,
  },
  eslint: {
    ignoreBuildErrors: true,
  },
  experimental: {
    serverActions: true,
    outputStandalone: true,
  },
};

module.exports = nextConfig;
