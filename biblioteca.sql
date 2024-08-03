-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 03/08/2024 às 18:30
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `biblioteca`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `livro`
--

CREATE TABLE `livro` (
  `id` bigint(20) NOT NULL,
  `autor` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `isbn` int(11) DEFAULT NULL,
  `preco` double NOT NULL,
  `titulo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `livro`
--

INSERT INTO `livro` (`id`, `autor`, `descricao`, `isbn`, `preco`, `titulo`) VALUES
(2, 'Jeff Kinney', 'Não é fácil ser criança. E ninguém sabe disso melhor do que Greg Heffley.', 123, 49.9, 'Diário de um Banana'),
(3, 'Jeff Kinney', 'Não é fácil ser criança. E ninguém sabe disso melhor do que Greg Heffley.', 456, 44.9, 'Diário de um Banana 2'),
(4, 'Jeff Kinney', 'Não é fácil ser criança. E ninguém sabe disso melhor do que Greg Heffley.', 345, 44.9, 'Diário de um Banana 3');

-- --------------------------------------------------------

--
-- Estrutura para tabela `papel`
--

CREATE TABLE `papel` (
  `id` bigint(20) NOT NULL,
  `papel` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `papel`
--

INSERT INTO `papel` (`id`, `papel`) VALUES
(1, 'ADMIN'),
(2, 'USER'),
(3, 'BIBLIOTECARIO');

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuario`
--

INSERT INTO `usuario` (`id`, `email`, `nome`, `password`) VALUES
(1, 'francapetshop@gmail.com', 'Felipe Soares', '123'),
(4, 'felipej3soaresj3@gmail.com', 'Felipe Soares', '123');

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario_papel`
--

CREATE TABLE `usuario_papel` (
  `usuario_id` bigint(20) NOT NULL,
  `papel_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `livro`
--
ALTER TABLE `livro`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `papel`
--
ALTER TABLE `papel`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `usuario_papel`
--
ALTER TABLE `usuario_papel`
  ADD KEY `FK75psa5ndkgp28gqowe8em3n6q` (`papel_id`),
  ADD KEY `FKpwds94wb43d01jx8l62ukbd67` (`usuario_id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `livro`
--
ALTER TABLE `livro`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `papel`
--
ALTER TABLE `papel`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `usuario_papel`
--
ALTER TABLE `usuario_papel`
  ADD CONSTRAINT `FK75psa5ndkgp28gqowe8em3n6q` FOREIGN KEY (`papel_id`) REFERENCES `papel` (`id`),
  ADD CONSTRAINT `FKpwds94wb43d01jx8l62ukbd67` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
